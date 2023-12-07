package com.choi.coffee_kiosks.view.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentHomeBinding
import com.choi.coffee_kiosks.util.common.HOME_FRAGMENT
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.showToastMessage
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
                val bundle = getBundle(HOME_FRAGMENT, getString(R.string.home_fragment_value))
                val fragment = KioskMainFragment()
                fragment.arguments = bundle
                binding.mainLayout.changeFragment(
                    this@HomeFragment,
                    fragment
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

            checkRewardCardView.onAvoidDuplicateClick {
                Toast.makeText(requireActivity(),"준비중 입니다",Toast.LENGTH_SHORT).show()
            }
        }
    }
}