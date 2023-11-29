package com.choi.coffee_kiosks.view.practice.main

import android.os.Bundle
import android.view.View
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentKioskMainBinding
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.view.practice.category.ChooseFragment

class KioskMainFragment :
    BaseFragment<FragmentKioskMainBinding>(FragmentKioskMainBinding::inflate) {

    // Todo 프래그먼트 인자 또는 SharePreference로 진입점 구분하기 (연습에서 ,미션에서)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backFloatingActionButton.onAvoidDuplicateClick {
                parentFragmentManager.popBackStack()
            }

            inStoreButton.onAvoidDuplicateClick {
                binding.mainLayout.changeFragment(
                    this@KioskMainFragment,
                    ChooseFragment()
                )
            }

            takeoutButton.setOnAvoidDuplicateClickWithFlow {
                binding.mainLayout.changeFragment(
                    this@KioskMainFragment,
                    ChooseFragment()
                )
            }
        }
    }
}