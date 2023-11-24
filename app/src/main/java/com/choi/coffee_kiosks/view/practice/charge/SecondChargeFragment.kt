package com.choi.coffee_kiosks.view.practice.charge

import android.os.Bundle
import android.view.View
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentSecondChargeBinding
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SecondChargeFragment :
    BaseFragment<FragmentSecondChargeBinding>(FragmentSecondChargeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            backButton.setOnAvoidDuplicateClickWithFlow {
                parentFragmentManager.popBackStack()
            }

            allCancelButton.setOnAvoidDuplicateClickWithFlow {
                parentFragmentManager.apply {
                    popBackStack()
                    popBackStack()
                }
                parentFragment?.view.apply {
                    val bottomSheet = this?.findViewById<View>(R.id.kioskBottomSheet)
                    val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)

                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        }
    }
}