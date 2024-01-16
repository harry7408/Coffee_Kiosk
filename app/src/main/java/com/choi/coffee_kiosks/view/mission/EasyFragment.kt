package com.choi.coffee_kiosks.view.mission

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.choi.coffee_kiosks.viewmodel.MainViewModel
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.MissionAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentEasyBinding
import com.choi.coffee_kiosks.network.FirebaseRetrofitManager
import com.choi.coffee_kiosks.view.practice.main.KioskMainFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EasyFragment : BaseFragment<FragmentEasyBinding>(FragmentEasyBinding::inflate) {
    private lateinit var missionAdapter: MissionAdapter
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val service = FirebaseRetrofitManager.missionService

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getEasyMission()
            val missionList = response.body()?.documents
            if (response.isSuccessful) {
                missionAdapter = MissionAdapter(missionList!!, showMission) {
                    // 미션에 대한 정보를 viewModel에 저장
                    mainViewModel.missionAnswer = it.fields.missionDetail.stringValue.toString()

                    parentFragment?.parentFragmentManager?.beginTransaction().apply {
                        parentFragment?.let { _ ->
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
                Toast.makeText(requireActivity(),"데이터가 없습니다",Toast.LENGTH_SHORT).show()
            }
        }

    }
}