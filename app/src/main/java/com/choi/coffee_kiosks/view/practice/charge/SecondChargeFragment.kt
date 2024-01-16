package com.choi.coffee_kiosks.view.practice.charge

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.entity.pref.TotalPricePreference
import com.choi.coffee_kiosks.databinding.FragmentSecondChargeBinding
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.view.practice.dialog.CardCheckDialogFragment
import com.choi.coffee_kiosks.viewmodel.PhoneNumberViewModel
import com.choi.coffee_kiosks.viewmodel.SelectedMenuViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shashank.sony.fancytoastlib.FancyToast

class SecondChargeFragment :
    BaseFragment<FragmentSecondChargeBinding>(FragmentSecondChargeBinding::inflate) {

    private val selectedMenuViewModel: SelectedMenuViewModel by activityViewModels()
    private lateinit var totalPricePreference: TotalPricePreference
    private val userPhoneNumberViewModel: PhoneNumberViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = requireContext()
        _binding = FragmentSecondChargeBinding.inflate(inflater, container, false)
        totalPricePreference = TotalPricePreference.getInstance(context)
        binding.orderAmountTextView.text =
            totalPricePreference.getData(TOTAL_PRICE).toString() + "원"
        binding.payAmountTextView.text = totalPricePreference.getData(TOTAL_PRICE).toString() + "원"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            backButton.setOnAvoidDuplicateClickWithFlow {
                parentFragmentManager.popBackStack()
            }

            allCancelButton.setOnAvoidDuplicateClickWithFlow {
                selectedMenuViewModel.clearMenu()
                totalPricePreference.clearData()
                userPhoneNumberViewModel.deleteNumber()
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

            payCouponCardView.setOnAvoidDuplicateClickWithFlow {
                errorMessage()
            }

            payStampCardView.setOnAvoidDuplicateClickWithFlow {
                errorMessage()
            }

            payCoCardView.setOnAvoidDuplicateClickWithFlow {
                errorMessage()
            }
            kaKaoPayCardView.setOnAvoidDuplicateClickWithFlow {
                errorMessage()
            }
            naverPayCardView.setOnAvoidDuplicateClickWithFlow {
                errorMessage()
            }

            payCardCardView.setOnAvoidDuplicateClickWithFlow {
                //todo 결제 화면 나오고 Dialog 띄운 후 미션 결과 보여주기
                val dialog=CardCheckDialogFragment()
                dialog.isCancelable=true
                dialog.show(parentFragmentManager,null)
            }
        }
    }

    private fun errorMessage() {
        FancyToast.makeText(
            requireActivity(),
            "준비중 입니다",
            FancyToast.LENGTH_SHORT, FancyToast.ERROR, false
        ).show()
    }
}