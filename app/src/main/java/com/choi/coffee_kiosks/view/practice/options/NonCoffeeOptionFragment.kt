package com.choi.coffee_kiosks.view.practice.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentNoncoffeeOptionBinding
import com.choi.coffee_kiosks.data.pref.NonFreeOptionPreference
import com.choi.coffee_kiosks.util.common.CREAM_OPTIONS
import com.choi.coffee_kiosks.util.common.CREAM_PRICE
import com.choi.coffee_kiosks.util.common.SHOT_OPTIONS
import com.choi.coffee_kiosks.util.common.SHOT_PRICE
import com.choi.coffee_kiosks.util.common.VANILLA_OPTIONS
import com.choi.coffee_kiosks.util.common.VANILLA_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class NonCoffeeOptionFragment(private val menu: String) :
    BaseDialog<FragmentNoncoffeeOptionBinding>(FragmentNoncoffeeOptionBinding::inflate) {

    private lateinit var nonFreePreference: NonFreeOptionPreference
    private var shotCount = 0
    private var vanillaCount = 0
    private var creamCount = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        _binding = FragmentNoncoffeeOptionBinding.inflate(inflater, container, false)
        this@NonCoffeeOptionFragment.setWindowSize(0.85, 0.85)
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

            vanillaPlusImageView.setOnAvoidDuplicateClickWithFlow {
                vanillaCount++
                vanillaCountTextView.text = vanillaCount.toString()
            }

            vanillaMinusImageView.setOnAvoidDuplicateClickWithFlow {
                vanillaCount--
                if (vanillaCount < 0) {
                    vanillaCount++
                }
                vanillaCountTextView.text = vanillaCount.toString()
            }

            creamPlusImageView.setOnAvoidDuplicateClickWithFlow {
                creamCount++
                creamCountTextView.text = creamCount.toString()
            }

            creamMinusImageView.setOnAvoidDuplicateClickWithFlow {
                creamCount--
                if (creamCount < 0) {
                    creamCount++
                }
                creamCountTextView.text = creamCount.toString()
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }

            okButton.setOnAvoidDuplicateClickWithFlow {
                var shotOption = binding.espressoOptionsTextView.text.toString() +
                        if (binding.espressoCountTextView.text.toString().toInt() > 0) {
                            binding.espressoCountTextView.text.toString()
                        } else "none"

                var vanillaOption = binding.vanillaOptionsTextView.text.toString() +
                        if (binding.vanillaCountTextView.text.toString().toInt() > 0) {
                            binding.vanillaCountTextView.text.toString()
                        } else "none"

                var creamOption = binding.creamOptionsTextView.text.toString() +
                        if (binding.creamCountTextView.text.toString().toInt() > 0) {
                            binding.creamCountTextView.text.toString()
                        } else "none"

                if (shotOption.contains("none")) {
                    shotOption = ""
                }
                if (vanillaOption.contains("none")) {
                    vanillaOption = ""
                }

                if (creamOption.contains("none")) {
                    creamOption = ""
                }

                val shotPrice = binding.espressoCountTextView.text.toString().toInt() * 500
                val vanillaPrice = binding.vanillaCountTextView.text.toString().toInt() * 500
                val creamPrice = binding.creamCountTextView.text.toString().toInt() * 900

                nonFreePreference.saveData(SHOT_OPTIONS, shotOption)
                nonFreePreference.saveData(VANILLA_OPTIONS, vanillaOption)
                nonFreePreference.saveData(CREAM_OPTIONS, creamOption)
                nonFreePreference.saveData(SHOT_PRICE, shotPrice.toString())
                nonFreePreference.saveData(VANILLA_PRICE, vanillaPrice.toString())
                nonFreePreference.saveData(CREAM_PRICE, creamPrice.toString())
                dismiss()
            }
        }
    }

    private fun initView() {
        with(binding) {
            menuTextView.text = menu

            espressoCountTextView.text =
                ((nonFreePreference.getData(SHOT_PRICE)?.toInt() ?: 0) / 500).toString()

            vanillaCountTextView.text =
                ((nonFreePreference.getData(VANILLA_PRICE)?.toInt() ?: 0) / 500).toString()

            creamCountTextView.text =
                ((nonFreePreference.getData(CREAM_PRICE)?.toInt() ?: 0) / 900).toString()
        }
    }
}