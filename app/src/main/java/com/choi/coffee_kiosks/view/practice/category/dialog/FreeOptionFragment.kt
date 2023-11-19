package com.choi.coffee_kiosks.view.practice.category.dialog

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.databinding.FragmentFreeOptionBinding
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow

class FreeOptionFragment: DialogFragment() {
    private lateinit var binding : FragmentFreeOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentFreeOptionBinding.inflate(inflater,container,false)
        val view=binding.root
        dialog?.setContentView(R.layout.fragment_free_option)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        val window = dialog?.window
        val size = Point()
        val display = window?.windowManager?.defaultDisplay
        display?.getSize(size)

        val width = size.x * 0.8
        val height = size.y * 0.8

        window?.setLayout(width.toInt(),height.toInt())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            okButton.setOnAvoidDuplicateClickWithFlow {
                //todo 데이터 전달
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }


        }
    }

}