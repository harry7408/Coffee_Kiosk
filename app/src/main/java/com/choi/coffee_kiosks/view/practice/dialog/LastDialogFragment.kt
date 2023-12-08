package com.choi.coffee_kiosks.view.practice.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.data.pref.TotalPricePreference
import com.choi.coffee_kiosks.databinding.FragmentLastDialogBinding
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.setWindowSize

class LastDialogFragment : DialogFragment() {
    private lateinit var binding : FragmentLastDialogBinding
    private lateinit var totalPricePreference: TotalPricePreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context=requireContext()
        binding= FragmentLastDialogBinding.inflate(inflater,container,false)
        this.setWindowSize(0.9,0.5)
        totalPricePreference=TotalPricePreference.getInstance(context)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            payAmountTextView.text=totalPricePreference.getData(TOTAL_PRICE).toString()+"원"
        }
    }

}