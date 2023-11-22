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
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentFreeOptionBinding
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class FreeOptionFragment: BaseDialog<FragmentFreeOptionBinding>(FragmentFreeOptionBinding::inflate) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentFreeOptionBinding.inflate(inflater,container,false)
        this@FreeOptionFragment.setWindowSize(0.8,0.8)
        return binding.root
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

    private fun initView() {

    }

}