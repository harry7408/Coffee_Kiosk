package com.choi.coffee_kiosks.view.home

import android.os.Bundle
import android.view.View
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentHomeBinding
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.view.mission.MissionFragment
import com.choi.coffee_kiosks.view.place.KioskPlaceInfoFragment
import com.choi.coffee_kiosks.view.practice.main.KioskMainFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            kiosksPracticeCardView.onAvoidDuplicateClick {
                binding.mainLayout.changeFragment(
                    this@HomeFragment,
                    KioskMainFragment()
                )
            }

            kiosksPlaceCheckCardView.onAvoidDuplicateClick {
                binding.mainLayout.changeFragment(
                    this@HomeFragment,
                    KioskPlaceInfoFragment()
                )
            }

            checkMissionCardView.onAvoidDuplicateClick {
                binding.mainLayout.changeFragment(
                    this@HomeFragment,
                    MissionFragment()
                )
            }
        }
    }
}