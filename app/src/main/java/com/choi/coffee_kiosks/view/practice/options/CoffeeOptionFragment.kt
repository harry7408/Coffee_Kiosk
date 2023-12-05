package com.choi.coffee_kiosks.view.practice.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentCoffeeOptionBinding
import com.choi.coffee_kiosks.data.pref.NonFreeOptionPreference
import com.choi.coffee_kiosks.util.common.HAZELNUT_OPTIONS
import com.choi.coffee_kiosks.util.common.HAZELNUT_PRICE
import com.choi.coffee_kiosks.util.common.PERL_OPTIONS
import com.choi.coffee_kiosks.util.common.PERL_PRICE
import com.choi.coffee_kiosks.util.common.VANILLA_OPTIONS
import com.choi.coffee_kiosks.util.common.VANILLA_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class CoffeeOptionFragment(private val menu : String) :
    BaseDialog<FragmentCoffeeOptionBinding>(FragmentCoffeeOptionBinding::inflate) {

    private lateinit var nonFreePreference: NonFreeOptionPreference
    private var hazelNutCount = 0
    private var perlCount = 0
    private var vanillaCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        _binding = FragmentCoffeeOptionBinding.inflate(inflater, container, false)
        this@CoffeeOptionFragment.setWindowSize(0.85, 0.85)
        nonFreePreference = NonFreeOptionPreference.getInstance(context)
        initView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            hazelnutPlusImageView.setOnAvoidDuplicateClickWithFlow {
                hazelNutCount++
                hazelnutCountTextView.text = hazelNutCount.toString()
            }

            hazelnutMinusImageView.setOnAvoidDuplicateClickWithFlow {
                hazelNutCount--
                if (hazelNutCount < 0) {
                    hazelNutCount++
                }
                hazelnutCountTextView.text = hazelNutCount.toString()
            }

            perlPlusImageView.setOnAvoidDuplicateClickWithFlow {
                perlCount++
                perlCountTextView.text = perlCount.toString()
            }

            perlMinusImageView.setOnAvoidDuplicateClickWithFlow {
                perlCount--
                if (perlCount < 0) {
                    perlCount++
                }
                perlCountTextView.text = perlCount.toString()
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

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }

            okButton.setOnAvoidDuplicateClickWithFlow {
                var hazelnutOption = binding.hazelnutOptionsTextView.text.toString() +
                        if (binding.hazelnutCountTextView.text.toString().toInt() > 0) {
                            binding.hazelnutCountTextView.text.toString()
                        } else "none"

                var perlOption = binding.perlOptionsTextView.text.toString() +
                        if (binding.perlCountTextView.text.toString().toInt() > 0) {
                            binding.perlCountTextView.text.toString()
                        } else "none"

                var vanillaOption = binding.vanillaOptionsTextView.text.toString() +
                        if (binding.vanillaCountTextView.text.toString().toInt() > 0) {
                            binding.vanillaCountTextView.text.toString()
                        } else "none"

                if (hazelnutOption.contains("none")) {
                    hazelnutOption = ""
                }
                if (perlOption.contains("none")) {
                    perlOption = ""
                }

                if (vanillaOption.contains("none")) {
                    vanillaOption = ""
                }

                val hazelnutPrice = binding.hazelnutCountTextView.text.toString().toInt() * 500
                val perlPrice = binding.perlCountTextView.text.toString().toInt() * 700
                val vanillaPrice = binding.vanillaCountTextView.text.toString().toInt() * 900

                nonFreePreference.saveData(HAZELNUT_OPTIONS, hazelnutOption)
                nonFreePreference.saveData(PERL_OPTIONS, perlOption)
                nonFreePreference.saveData(VANILLA_OPTIONS, vanillaOption)
                nonFreePreference.saveData(HAZELNUT_PRICE, hazelnutPrice.toString())
                nonFreePreference.saveData(PERL_PRICE, perlPrice.toString())
                nonFreePreference.saveData(VANILLA_PRICE, vanillaPrice.toString())
                dismiss()
            }
        }
    }

    private fun initView() {
        with(binding) {
            menuTextView.text=menu

            hazelnutCountTextView.text =
                ((nonFreePreference.getData(HAZELNUT_PRICE)?.toInt() ?: 0) / 500).toString()

            perlCountTextView.text =
                ((nonFreePreference.getData(PERL_PRICE)?.toInt() ?: 0) / 700).toString()

            vanillaCountTextView.text =
                ((nonFreePreference.getData(VANILLA_PRICE)?.toInt() ?: 0) / 500).toString()
        }
    }
}