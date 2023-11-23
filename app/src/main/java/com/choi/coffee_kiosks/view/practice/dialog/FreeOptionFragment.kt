package com.choi.coffee_kiosks.view.practice.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentFreeOptionBinding
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize

class FreeOptionFragment(private val menuName: String) :
    BaseDialog<FragmentFreeOptionBinding>(FragmentFreeOptionBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFreeOptionBinding.inflate(inflater, container, false)
        this@FreeOptionFragment.setWindowSize(0.8, 0.8)

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
            if (menuName.contains("Ice")) {
                iceTextView.visibility=View.VISIBLE
                divider2.visibility=View.VISIBLE
                line2.visibility=View.VISIBLE
                iceChipGroup.visibility=View.VISIBLE
            } else {
                iceTextView.visibility=View.GONE
                divider2.visibility=View.GONE
                line2.visibility=View.GONE
                iceChipGroup.visibility=View.GONE
            }
        }
    }

}