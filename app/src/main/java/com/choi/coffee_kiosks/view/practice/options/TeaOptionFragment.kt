package com.choi.coffee_kiosks.view.practice.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentTeaOptionBinding
import com.choi.coffee_kiosks.entity.pref.NonFreeOptionPreference
import com.choi.coffee_kiosks.util.common.SHOT_OPTIONS
import com.choi.coffee_kiosks.util.common.SHOT_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class TeaOptionFragment(private val menu : String) :
    BaseDialog<FragmentTeaOptionBinding>(FragmentTeaOptionBinding::inflate) {

    private lateinit var nonFreePreference: NonFreeOptionPreference
    private var shotCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        _binding = FragmentTeaOptionBinding.inflate(inflater, container, false)
        this@TeaOptionFragment.setWindowSize(0.85, 0.85)
        nonFreePreference = NonFreeOptionPreference.getInstance(context)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            espressoPlusImageView.setOnAvoidDuplicateClickWithFlow {
                shotCount++
                espressoCountTextView.text = shotCount.toString()
            }

            espressoMinusImageView.setOnAvoidDuplicateClickWithFlow {
                shotCount--
                if (shotCount < 0) {
                    shotCount++
                }
                espressoCountTextView.text = shotCount.toString()
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }

            okButton.setOnAvoidDuplicateClickWithFlow {
                var shotOption = binding.espressoOptionsTextView.text.toString() +
                        if (binding.espressoCountTextView.text.toString().toInt() > 0) {
                            binding.espressoCountTextView.text.toString()
                        } else "none"

                if (shotOption.contains("none")) {
                    shotOption = ""
                }

                val shotPrice = binding.espressoCountTextView.text.toString().toInt() * 500

                nonFreePreference.saveData(SHOT_OPTIONS, shotOption)
                nonFreePreference.saveData(SHOT_PRICE, shotPrice.toString())

                dismiss()
            }
        }
    }


    private fun initView() {
        with(binding) {
            menuTextView.text=menu

            espressoCountTextView.text =
                ((nonFreePreference.getData(SHOT_PRICE)?.toInt() ?: 0) / 500).toString()
        }
    }
}