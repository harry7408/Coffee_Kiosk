package com.choi.coffee_kiosks.view.practice.charge

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.data.User
import com.choi.coffee_kiosks.data.pref.TotalPricePreference
import com.choi.coffee_kiosks.databinding.FragmentSecondChargeBinding
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.viewModels.PhoneNumberViewModel
import com.choi.coffee_kiosks.viewModels.SelectedMenuViewModel
import com.choi.coffee_kiosks.viewModels.TotalPriceViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SecondChargeFragment :
    BaseFragment<FragmentSecondChargeBinding>(FragmentSecondChargeBinding::inflate) {

    private val selectedMenuViewModel:SelectedMenuViewModel by activityViewModels()
    private lateinit var totalPricePreference: TotalPricePreference
    private val userPhoneNumberViewModel: PhoneNumberViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context=requireContext()
        _binding= FragmentSecondChargeBinding.inflate(inflater,container,false)
        totalPricePreference=TotalPricePreference.getInstance(context)
        binding.orderAmountTextView.text=totalPricePreference.getData(TOTAL_PRICE).toString()+"원"
        binding.payAmountTextView.text=totalPricePreference.getData(TOTAL_PRICE).toString()+"원"
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
            }

        }
    }
    
    private fun errorMessage() {
        Toast.makeText(requireActivity(),"준비중 입니다",Toast.LENGTH_SHORT).show()
    }
}