package com.choi.coffee_kiosks.view.practice.category.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.databinding.FragmentShowOptionsBinding
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow

class ShowOptionsFragment(private val menu: String, var price: Long, val imgId: Int) :
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

        dialog?.setContentView(R.layout.fragment_show_options)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window = dialog?.window
        val size = Point()
        val display = window?.windowManager?.defaultDisplay
        display?.getSize(size)

        val width = size.x * 0.85
        val height = size.y * 0.85

        window?.setLayout(width.toInt(),height.toInt())

        with(binding) {
            menuTextView.text = menu
            countTextView.text = currentCount.toString()
            amountTextView.text = "$price 원"
            menuImageView.setImageResource(imgId)
        }

        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            coldButton.apply {
                setOnAvoidDuplicateClickWithFlow {
                    menuTextView.text = "$menu(Ice)"
                    setBackgroundResource(R.drawable.background_text_choose)
                }
            }

            hotButton.setOnAvoidDuplicateClickWithFlow {
                menuTextView.text = "$menu(Hot)"
            }

            plusImageView.setOnAvoidDuplicateClickWithFlow {
                currentCount++
                countTextView.text = currentCount.toString()
                amountTextView.text = "${price * currentCount} 원"
            }

            minusImageView.setOnAvoidDuplicateClickWithFlow {
                currentCount--
                if (currentCount==0) {
                    currentCount++
                }
                countTextView.text = currentCount.toString()
                amountTextView.text = "${price * currentCount} 원"
            }

            okButton.setOnAvoidDuplicateClickWithFlow {
                //todo 데이터 전달
            }

            addFreeTextView.setOnAvoidDuplicateClickWithFlow {
                val dialog=FreeOptionFragment()
                dialog.show(childFragmentManager,"tttt")
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }
        }
    }

}