package com.choi.coffee_kiosks.view.practice.category.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.databinding.FragmentShowOptionsBinding
import com.choi.coffee_kiosks.model.Menu
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class ShowOptionsFragment(private val menu : Menu) :
    DialogFragment() {
    private lateinit var binding: FragmentShowOptionsBinding
    private var currentCount = 1

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowOptionsBinding.inflate(inflater, container, false)
        val view = binding.root

        this@ShowOptionsFragment.setWindowSize(0.85, 0.85)
        initView()
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            coldButton.apply {
                setOnAvoidDuplicateClickWithFlow {

                    menuTextView.text = "${menu.name}(Ice)"
                    setBackgroundResource(R.drawable.background_text_choose)
                }
            }

            hotButton.setOnAvoidDuplicateClickWithFlow {
                menuTextView.text = "${menu.name}(Hot)"
            }

            plusImageView.setOnAvoidDuplicateClickWithFlow {
                currentCount++
                countTextView.text = currentCount.toString()
                amountTextView.text = "${menu.price * currentCount} 원"
            }

            minusImageView.setOnAvoidDuplicateClickWithFlow {
                currentCount--
                if (currentCount == 0) {
                    currentCount++
                }
                countTextView.text = currentCount.toString()
                amountTextView.text = "${menu.price * currentCount} 원"
            }

            okButton.setOnAvoidDuplicateClickWithFlow {
                //todo 데이터 전달
            }

            addFreeTextView.setOnAvoidDuplicateClickWithFlow {
                val dialog = FreeOptionFragment(binding.menuTextView.text.toString())
                dialog.isCancelable=true
                dialog.show(childFragmentManager,null)
                Log.d("Options",binding.menuTextView.text.toString())
            }

            addPayTextView.setOnAvoidDuplicateClickWithFlow {
                val dialog=PayOptionFragment(menu.type)
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }
        }
    }

    private fun initView() {
        with(binding) {
            menuTextView.text = menu.name
            countTextView.text = currentCount.toString()
            amountTextView.text = "${menu.price} 원"
            menuImageView.setImageResource(menu.image)
        }
    }

}