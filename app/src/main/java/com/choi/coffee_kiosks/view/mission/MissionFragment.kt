package com.choi.coffee_kiosks.view.mission

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.MissionAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentMissionBinding
import com.choi.coffee_kiosks.rest.FirebaseRetrofitManager
import com.choi.coffee_kiosks.util.common.showToastMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MissionFragment : BaseFragment<FragmentMissionBinding>(FragmentMissionBinding::inflate) {
    private lateinit var missionAdapter: MissionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = FirebaseRetrofitManager.missionService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getMission()
            val missionList = response.body()?.documents
            if (response.isSuccessful) {
                missionAdapter = MissionAdapter(missionList!!) {
                    Log.d("TAG","ASDFAD")
                }

               CoroutineScope(Dispatchers.Main).launch {
                   binding.missionRecyclerView.apply {
                       adapter = missionAdapter
                       layoutManager =
                           LinearLayoutManager(
                               requireContext(),
                               LinearLayoutManager.VERTICAL,
                               false
                           )
                   }
               }

            } else {
                requireContext().showToastMessage(getString(R.string.mission_not_exists))
            }
        }

    }


}