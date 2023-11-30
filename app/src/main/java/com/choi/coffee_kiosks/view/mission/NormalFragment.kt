package com.choi.coffee_kiosks.view.mission

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.MissionAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentNormalBinding
import com.choi.coffee_kiosks.network.FirebaseRetrofitManager
import com.choi.coffee_kiosks.util.common.showToastMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NormalFragment : BaseFragment<FragmentNormalBinding>(FragmentNormalBinding::inflate) {
    private lateinit var missionAdapter: MissionAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = FirebaseRetrofitManager.missionService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getNormalMission()
            val missionList = response.body()?.documents
            if (response.isSuccessful) {
                missionAdapter = MissionAdapter(missionList!!, showMission) {

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