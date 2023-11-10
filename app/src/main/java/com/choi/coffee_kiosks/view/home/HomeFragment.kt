package com.choi.coffee_kiosks.view.home

import android.os.Bundle
import android.view.View
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentHomeBinding
import com.choi.coffee_kiosks.view.mission.MissionFragment
import com.choi.coffee_kiosks.view.place.KioskPlaceInfoFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            kiosksPlaceCheckCardView.onAvoidDuplicateClick {
                parentFragmentManager.beginTransaction()
                    .add(binding.homeLayout.id, KioskPlaceInfoFragment())
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }

            checkMissionCardView.onAvoidDuplicateClick {
                parentFragmentManager.beginTransaction()
                    .add(binding.homeLayout.id, MissionFragment())
                    .addToBackStack(null)
                    .commitAllowingStateLoss()
            }

        }
    }

}