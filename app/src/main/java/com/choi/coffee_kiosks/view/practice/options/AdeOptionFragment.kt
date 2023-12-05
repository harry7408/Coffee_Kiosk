package com.choi.coffee_kiosks.view.practice.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentAdeOptionBinding
import com.choi.coffee_kiosks.data.pref.NonFreeOptionPreference
import com.choi.coffee_kiosks.util.common.CREAM_OPTIONS
import com.choi.coffee_kiosks.util.common.CREAM_PRICE
import com.choi.coffee_kiosks.util.common.PERL_OPTIONS
import com.choi.coffee_kiosks.util.common.PERL_PRICE
import com.choi.coffee_kiosks.util.common.SHOT_OPTIONS
import com.choi.coffee_kiosks.util.common.SHOT_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class AdeOptionFragment(private val menu: String) :
    BaseDialog<FragmentAdeOptionBinding>(FragmentAdeOptionBinding::inflate) {

    private lateinit var nonFreePreference: NonFreeOptionPreference
    private var shotCount = 0
    private var perlCount = 0
    private var creamCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        _binding = FragmentAdeOptionBinding.inflate(inflater, container, false)
        this@AdeOptionFragment.setWindowSize(0.85, 0.85)
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

                var perlOption = binding.perlOptionsTextView.text.toString() +
                        if (binding.perlCountTextView.text.toString().toInt() > 0) {
                            binding.perlCountTextView.text.toString()
                        } else "none"

                var creamOption = binding.creamOptionsTextView.text.toString() +
                        if (binding.creamCountTextView.text.toString().toInt() > 0) {
                            binding.creamCountTextView.text.toString()
                        } else "none"

                if (shotOption.contains("none")) {
                    shotOption = ""
                }
                if (perlOption.contains("none")) {
                    perlOption = ""
                }

                if (creamOption.contains("none")) {
                    creamOption = ""
                }

                val shotPrice = binding.espressoCountTextView.text.toString().toInt() * 500
                val perlPrice = binding.perlCountTextView.text.toString().toInt() * 700
                val creamPrice = binding.creamCountTextView.text.toString().toInt() * 900

                nonFreePreference.saveData(SHOT_OPTIONS, shotOption)
                nonFreePreference.saveData(PERL_OPTIONS, perlOption)
                nonFreePreference.saveData(CREAM_OPTIONS, creamOption)
                nonFreePreference.saveData(SHOT_PRICE, shotPrice.toString())
                nonFreePreference.saveData(PERL_PRICE, perlPrice.toString())
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

            perlCountTextView.text =
                ((nonFreePreference.getData(PERL_PRICE)?.toInt() ?: 0) / 700).toString()

            creamCountTextView.text =
                ((nonFreePreference.getData(CREAM_PRICE)?.toInt() ?: 0) / 900).toString()
        }
    }
}