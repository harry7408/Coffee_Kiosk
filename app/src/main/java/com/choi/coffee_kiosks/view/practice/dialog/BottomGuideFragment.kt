package com.choi.coffee_kiosks.view.practice.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.SelectedMenuAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentBottomGuideBinding
import com.choi.coffee_kiosks.model.SelectedMenu
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.view.practice.charge.FirstChargeFragment
import com.choi.coffee_kiosks.viewModels.SelectedMenuViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomGuideFragment : BaseFragment<FragmentBottomGuideBinding>
    (FragmentBottomGuideBinding::inflate) {

    private val selectedMenuViewModel: SelectedMenuViewModel by activityViewModels()
    private lateinit var selectedAdapter: SelectedMenuAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // 결제 하기 눌렀을 때 동작
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

        binding.kioskListRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            observerSetUp()
        }
    }

    private fun observerSetUp() {
        selectedMenuViewModel.selectedMenus.observe(viewLifecycleOwner) { menus ->
            menus?.let {
                updateUI(menus)
            }
        }
    }

    private fun updateUI(menus: List<SelectedMenu>) {
        selectedAdapter = SelectedMenuAdapter(menus)
        binding.kioskListRecyclerView.adapter=selectedAdapter
    }
}

