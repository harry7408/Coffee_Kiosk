package com.choi.coffee_kiosks.view.practice.charge

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentFirstChargeBinding
import com.choi.coffee_kiosks.data.pref.TotalPricePreference
import com.choi.coffee_kiosks.databinding.ItemSelectedMenuBinding
import com.choi.coffee_kiosks.util.common.JOIN_KEY
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.util.common.READ_KEY
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.showToastMessage
import com.choi.coffee_kiosks.view.practice.dialog.SearchAndJoinFragment
import com.choi.coffee_kiosks.viewModels.PhoneNumberViewModel
import com.choi.coffee_kiosks.viewModels.SelectedMenuViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class FirstChargeFragment :
    BaseFragment<FragmentFirstChargeBinding>(FragmentFirstChargeBinding::inflate) {

    private lateinit var totalPricePreference: TotalPricePreference
    private val selectedMenuViewModel: SelectedMenuViewModel by activityViewModels()
    private val phoneNumberViewModel: PhoneNumberViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        _binding = FragmentFirstChargeBinding.inflate(inflater, container, false)
        totalPricePreference = TotalPricePreference.getInstance(context)
        initView()
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
                phoneNumberViewModel.deleteNumber()
                backPressed()
            }

            phoneCheckCardView.setOnAvoidDuplicateClickWithFlow {
                // todo 번호 조회로 이동
                val dialog = SearchAndJoinFragment().apply {
                    arguments = Bundle().apply {
                        putString(READ_KEY, "조회")
                    }
                }
                dialog.isCancelable = true
                dialog.show(childFragmentManager, null)
            }

            barcodeCheckCardView.setOnAvoidDuplicateClickWithFlow {
                // Toast 메세지
                Toast.makeText(requireActivity(), "준비중 입니다", Toast.LENGTH_SHORT).show()

            }

            userAddCardView.setOnAvoidDuplicateClickWithFlow {
                // todo 번호 입력 으로 이동
                val dialog = SearchAndJoinFragment().apply {
                    arguments = Bundle().apply {
                        putString(JOIN_KEY, "가입")
                    }
                }
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
            orderAmountTextView.text = totalPricePreference.getData(TOTAL_PRICE).toString() + "원"
            discountAmountTextView.text = "0원"
            payAmountTextView.text = totalPricePreference.getData(TOTAL_PRICE).toString() + "원"

            phoneNumberViewModel.phoneNumber.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    addUserInfo()
                    binding.phoneNumberTextView.text=it.replaceRange(4..7,"****")
                }
            }
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

    private fun initView() {
        with(binding) {
            userNameGuideTextView.visibility = View.GONE
            phoneNumberGuideTextView.visibility = View.GONE
            stampCountGuideTextView.visibility = View.GONE
            userNameTextView.visibility = View.GONE
            phoneNumberTextView.visibility = View.GONE
            stampCountTextView.visibility = View.GONE
        }
    }

    private fun addUserInfo() {
        with(binding) {
            userNameGuideTextView.visibility = View.VISIBLE
            phoneNumberGuideTextView.visibility = View.VISIBLE
            stampCountGuideTextView.visibility = View.VISIBLE
            userNameTextView.visibility = View.VISIBLE
            phoneNumberTextView.visibility = View.VISIBLE
            stampCountTextView.visibility = View.VISIBLE
        }
    }
}