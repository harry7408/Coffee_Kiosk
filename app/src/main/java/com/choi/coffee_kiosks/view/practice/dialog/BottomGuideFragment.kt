package com.choi.coffee_kiosks.view.practice.dialog

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.adapter.SelectedMenuAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentBottomGuideBinding
import com.choi.coffee_kiosks.entity.SelectedMenu
import com.choi.coffee_kiosks.entity.pref.TotalPricePreference
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.view.practice.charge.FirstChargeFragment
import com.choi.coffee_kiosks.viewmodel.SelectedMenuViewModel
import com.choi.coffee_kiosks.viewmodel.TotalPriceViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomGuideFragment : BaseFragment<FragmentBottomGuideBinding>
    (FragmentBottomGuideBinding::inflate) {

    private lateinit var totalPriceViewModel: TotalPriceViewModel
    private val selectedMenuViewModel: SelectedMenuViewModel by activityViewModels()
    private lateinit var selectedAdapter: SelectedMenuAdapter

    private lateinit var totalPricePreference: TotalPricePreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context=requireContext()
        _binding= FragmentBottomGuideBinding.inflate(inflater,container,false)
        totalPricePreference= TotalPricePreference.getInstance(context)
        totalPriceViewModel= TotalPriceViewModel(totalPricePreference)
        return binding.root
    }

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
            addItemDecoration(RecyclerDecoration(20),LinearLayoutManager.HORIZONTAL)
        }

        totalPriceViewModel.totalAmount.observe(viewLifecycleOwner) {
            binding.totalAmountTextView.text="총 결제 금액 \n ${it}원"
        }

        binding.allCancelTextView.setOnAvoidDuplicateClickWithFlow {
            selectedMenuViewModel.clearMenu()
            totalPriceViewModel.clearPrice()
        }
    }

    private fun observerSetUp() {
        selectedMenuViewModel.selectedMenus.observe(viewLifecycleOwner) { menus ->
            menus?.let {
                updateUI(menus)

                val totalPrice = menus.sumBy { it.price }
                totalPriceViewModel.updateTotalAmount(totalPrice)
                totalPricePreference.saveData(TOTAL_PRICE, totalPrice)
            }
        }
    }

    private fun updateUI(menus: MutableList<SelectedMenu>) {
        selectedAdapter = SelectedMenuAdapter(menus) {price->
            totalPriceViewModel.subtractAmount(price)
            totalPricePreference.saveData(TOTAL_PRICE,totalPricePreference.getData(TOTAL_PRICE)-price)
        }
        binding.kioskListRecyclerView.adapter=selectedAdapter
    }


    inner class RecyclerDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val position=parent.getChildAdapterPosition(view)
            if (position!=RecyclerView.NO_POSITION) {
                outRect.right=space
            }
        }
    }
}

