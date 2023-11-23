package com.choi.coffee_kiosks.view.practice.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentBottomGuideBinding
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.view.practice.charge.FirstChargeFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomGuideFragment : BaseFragment<FragmentBottomGuideBinding>
    (FragmentBottomGuideBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.chargeImageView.setOnAvoidDuplicateClickWithFlow {
            parentFragment?.view.apply {
                val fragmentContainer =
                    this?.findViewById<FragmentContainerView>(R.id.fragmentContainer)
                fragmentContainer?.changeFragment(this@BottomGuideFragment, FirstChargeFragment())

                val bottomSheet = this?.findViewById<View>(R.id.kioskBottomSheet)
                val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)

                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }

        }
    }
}
