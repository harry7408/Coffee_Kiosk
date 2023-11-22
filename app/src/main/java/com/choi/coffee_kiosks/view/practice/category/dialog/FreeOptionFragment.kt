package com.choi.coffee_kiosks.view.practice.category.dialog

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentFreeOptionBinding
import com.choi.coffee_kiosks.model.Menu
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize
import com.choi.coffee_kiosks.util.common.showToastMessage

class FreeOptionFragment(private val menuName: String) :
    BaseDialog<FragmentFreeOptionBinding>(FragmentFreeOptionBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFreeOptionBinding.inflate(inflater, container, false)
        this@FreeOptionFragment.setWindowSize(0.8, 0.8)
        Log.d("dkdk",menuName)

        initView(menuName)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            okButton.setOnAvoidDuplicateClickWithFlow {
                //todo 데이터 전달

            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                //todo 이전에 클릭해 놓을 것이 있다면 해제 해줘야 한다.
                dismiss()
            }
        }
    }

    private fun initView(menuName: String) {
        with(binding) {
            menuTextView.text=menuName
            if (menuName.contains("Hot")) {
                iceTextView.visibility=View.GONE
                divider2.visibility=View.GONE
                line2.visibility=View.GONE
                iceChipGroup.visibility=View.GONE
            } else {
                iceTextView.visibility=View.VISIBLE
                divider2.visibility=View.VISIBLE
                line2.visibility=View.VISIBLE
                iceChipGroup.visibility=View.VISIBLE
            }
        }
    }

}