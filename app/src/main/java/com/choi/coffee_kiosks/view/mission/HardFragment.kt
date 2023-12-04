package com.choi.coffee_kiosks.view.mission

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.choi.coffee_kiosks.viewModels.MainViewModel
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.MissionAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentHardBinding
import com.choi.coffee_kiosks.network.FirebaseRetrofitManager
import com.choi.coffee_kiosks.util.common.showToastMessage
import com.choi.coffee_kiosks.view.practice.main.KioskMainFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HardFragment : BaseFragment<FragmentHardBinding>(FragmentHardBinding::inflate) {
    private lateinit var missionAdapter: MissionAdapter
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = FirebaseRetrofitManager.missionService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getHardMission()
            val missionList = response.body()?.documents
            if (response.isSuccessful) {
                missionAdapter = MissionAdapter(missionList!!, showMission) {
                    mainViewModel.missionAnswer = it.fields.missionDetail.stringValue.toString()

                    parentFragment?.parentFragmentManager?.beginTransaction().apply {
                        parentFragment?.let { it1 ->
                            this?.add(R.id.layoutMission, KioskMainFragment())
                                ?.addToBackStack(null)
                                ?.commit()
                        }
                    }
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