package com.choi.coffee_kiosks.view.practice.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentFreeOptionBinding
import com.choi.coffee_kiosks.data.pref.FreeOptionPreference
import com.choi.coffee_kiosks.util.common.DENSITY_OPTION
import com.choi.coffee_kiosks.util.common.ICE_OPTION
import com.choi.coffee_kiosks.util.common.SUGAR_OPTION
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize
import com.google.android.material.chip.Chip

class FreeOptionFragment(private val menuName: String) :
    BaseDialog<FragmentFreeOptionBinding>(FragmentFreeOptionBinding::inflate) {

    private lateinit var freePreference: FreeOptionPreference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        freePreference = FreeOptionPreference.getInstance(context)
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

    private fun putOptions() {
        var sugarOptionsText =
            binding.sugarTextView.text.toString() + when (binding.sugarChipGroup.checkedChipId) {
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

        var iceOptionsText =
            binding.iceTextView.text.toString() + when (binding.iceChipGroup.checkedChipId) {
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

        var densityOptionsText =
            binding.densityTextView.text.toString() + if (binding.weakChip.isChecked) {
                binding.weakChip.text.toString()
            } else "none"

        if (sugarOptionsText.contains("none")) {
            sugarOptionsText = ""
        }
        if (iceOptionsText.contains("none")) {
            iceOptionsText = ""
        }
        if (densityOptionsText.contains("none")) {
            densityOptionsText = ""
        }

        freePreference.saveData(ICE_OPTION, iceOptionsText)
        freePreference.saveData(SUGAR_OPTION, sugarOptionsText)
        freePreference.saveData(DENSITY_OPTION, densityOptionsText)

    }

    private fun initPreviousSelect() {
        val sugarOption = freePreference.getData(SUGAR_OPTION).toString()
        val iceOption = freePreference.getData(ICE_OPTION).toString()
        val densityOption = freePreference.getData(DENSITY_OPTION).toString()

        with(binding) {
            for (c in 0 until sugarChipGroup.childCount) {
                val chip = sugarChipGroup.getChildAt(c) as Chip
                if (sugarOption.contains(chip.text.toString())) {
                    chip.isChecked = true
                    break
                }
            }

            for (c in 0 until iceChipGroup.childCount) {
                val chip = iceChipGroup.getChildAt(c) as Chip
                if (iceOption.contains(chip.text.toString())) {
                    chip.isChecked = true
                    break
                }
            }

            if (densityOption.contains(weakChip.text.toString())) {
                weakChip.isChecked = true
            }
        }
    }

    private fun initView(menuName: String) {
        initPreviousSelect()
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