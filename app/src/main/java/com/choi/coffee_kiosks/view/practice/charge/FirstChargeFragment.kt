package com.choi.coffee_kiosks.view.practice.charge

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentFirstChargeBinding
import com.choi.coffee_kiosks.data.pref.TotalPricePreference
import com.choi.coffee_kiosks.databinding.ItemSelectedMenuBinding
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.view.practice.dialog.SearchAndJoinFragment
import com.choi.coffee_kiosks.viewModels.SelectedMenuViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class FirstChargeFragment :
    BaseFragment<FragmentFirstChargeBinding>(FragmentFirstChargeBinding::inflate) {

    private lateinit var totalPricePreference: TotalPricePreference
    private val selectedMenuViewModel: SelectedMenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context=requireContext()
        _binding= FragmentFirstChargeBinding.inflate(inflater,container,false)
        totalPricePreference= TotalPricePreference.getInstance(context)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            backButton.setOnAvoidDuplicateClickWithFlow {
                backPressed()
            }

            allCancelButton.setOnAvoidDuplicateClickWithFlow {
                selectedMenuViewModel.clearMenu()
                totalPricePreference.clearData()
                backPressed()
            }

            phoneCheckCardView.setOnAvoidDuplicateClickWithFlow {
                // todo 번호 조회로 이동
                val dialog = SearchAndJoinFragment()
                dialog.isCancelable = true
                dialog.show(childFragmentManager, null)
            }

            barcodeCheckCardView.setOnAvoidDuplicateClickWithFlow {
                //todo 바코드 스캔 라이브러리 알아보기
            }

            userAddCardView.setOnAvoidDuplicateClickWithFlow {
                // todo 번호 입력 으로 이동
                val dialog = SearchAndJoinFragment()
                dialog.isCancelable = true
                dialog.show(childFragmentManager, null)
            }

            nextButton.setOnAvoidDuplicateClickWithFlow {
                parentFragment?.view.apply {
                    val fragmentContainer =
                        this?.findViewById<FragmentContainerView>(R.id.fragmentContainer)
                    fragmentContainer?.changeFragment(
                        this@FirstChargeFragment,
                        SecondChargeFragment()
                    )

                }
            }
            orderAmountTextView.text=totalPricePreference.getData(TOTAL_PRICE).toString()+"원"
            discountAmountTextView.text="0원"
            payAmountTextView.text=totalPricePreference.getData(TOTAL_PRICE).toString()+"원"
        }

    }

    private fun backPressed() {
        parentFragmentManager.popBackStack()
        parentFragment?.view.apply {
            val bottomSheet = this?.findViewById<View>(R.id.kioskBottomSheet)
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)

            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}