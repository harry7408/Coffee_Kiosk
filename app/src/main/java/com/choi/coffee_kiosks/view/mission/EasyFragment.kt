package com.choi.coffee_kiosks.view.mission

import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentEasyBinding

class EasyFragment : BaseFragment<FragmentEasyBinding>(FragmentEasyBinding::inflate) {
//    private lateinit var missionAdapter: MissionAdapter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
    /*    val service = FirebaseRetrofitManager.missionService

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
        }*/

//    }
}