package com.choi.coffee_kiosks.view.practice.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentKioskMainBinding
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.view.practice.choose.ChooseFragment

class KioskMainFragment :
    BaseFragment<FragmentKioskMainBinding>(FragmentKioskMainBinding::inflate) {

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