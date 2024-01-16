package com.choi.coffee_kiosks.view.practice.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.entity.pref.TotalPricePreference
import com.choi.coffee_kiosks.databinding.FragmentCardCheckDialogBinding
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class CardCheckDialogFragment: DialogFragment() {
    private lateinit var binding: FragmentCardCheckDialogBinding

    private lateinit var totalPricePreference: TotalPricePreference

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context=requireContext()
        binding=FragmentCardCheckDialogBinding.inflate(inflater,container,false)
        this.setWindowSize(0.9,0.9)
        totalPricePreference=TotalPricePreference.getInstance(context)

        binding.fullPriceTextView.text=totalPricePreference.getData(TOTAL_PRICE).toString()+"원"
        binding.payPriceTextView.text=totalPricePreference.getData(TOTAL_PRICE).toString()+"원"
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }

            nextButton.setOnAvoidDuplicateClickWithFlow {
                // 카드 삽입 요청
                val dialog=RequestCardFragment()
                dialog.isCancelable=true
                dialog.show(parentFragmentManager,null)

                this@CardCheckDialogFragment.dismiss()

            }
        }
    }
}