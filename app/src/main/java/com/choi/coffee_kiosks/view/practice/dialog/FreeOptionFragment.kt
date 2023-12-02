package com.choi.coffee_kiosks.view.practice.dialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentFreeOptionBinding
import com.choi.coffee_kiosks.model.pref.FreeOptionPreference
import com.choi.coffee_kiosks.util.common.ICE_OPTION
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class FreeOptionFragment(private val menuName: String) :
    BaseDialog<FragmentFreeOptionBinding>(FragmentFreeOptionBinding::inflate) {

    private lateinit var freePreference : FreeOptionPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context=requireContext()
        freePreference=FreeOptionPreference(context)
        _binding = FragmentFreeOptionBinding.inflate(inflater, container, false)
        this@FreeOptionFragment.setWindowSize(0.8, 0.8)

        initView(menuName)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            okButton.setOnAvoidDuplicateClickWithFlow {
                putOptions()
                dismiss()
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }
        }
    }

    private fun putOptions()  {
        var sugarOptionsText = binding.sugarTextView.text.toString()+" "+ when(binding.sugarChipGroup.checkedChipId) {
            R.id.pumpOneChip -> {
                binding.pumpOneChip.text
            }

            R.id.pumpTwoChip -> {
                binding.pumpTwoChip.text
            }

            R.id.pumpThreeChip -> {
                binding.pumpThreeChip.text
            }

            else -> {
                "none"
            }
        }

        freePreference.saveData(ICE_OPTION,sugarOptionsText)

        var iceOptionsText = when (binding.iceChipGroup.checkedChipId) {
            R.id.manyChip -> {
                binding.manyChip.text.toString()
            }

            R.id.lessChip -> {
                binding.lessChip.text.toString()
            }

            else -> {
                "none"
            }
        }

        var densityOptionsText = if (binding.weakChip.isChecked) {
            binding.weakChip.text.toString()
        } else "none"

        if (sugarOptionsText == "none") {
            sugarOptionsText = ""
        }
        if (iceOptionsText == "none") {
            iceOptionsText = ""
        }
        if (densityOptionsText == "none") {
            densityOptionsText = ""
        }


    }

    private fun initView(menuName: String) {
        with(binding) {
            menuTextView.text = menuName
            if (menuName.contains("Ice")) {
                iceTextView.visibility = View.VISIBLE
                divider2.visibility = View.VISIBLE
                line2.visibility = View.VISIBLE
                iceChipGroup.visibility = View.VISIBLE
            } else {
                iceTextView.visibility = View.GONE
                divider2.visibility = View.GONE
                line2.visibility = View.GONE
                iceChipGroup.visibility = View.GONE
            }
        }
    }
}