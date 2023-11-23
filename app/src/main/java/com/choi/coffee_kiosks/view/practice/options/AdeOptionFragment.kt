package com.choi.coffee_kiosks.view.practice.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentAdeOptionBinding
import com.choi.coffee_kiosks.databinding.FragmentFreeOptionBinding
import com.choi.coffee_kiosks.util.common.setWindowSize

class AdeOptionFragment(private val menu: String) :
    BaseDialog<FragmentAdeOptionBinding>(FragmentAdeOptionBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdeOptionBinding.inflate(inflater, container, false)
        this@AdeOptionFragment.setWindowSize(0.85, 0.85)
        initView()
        return binding.root
    }



    private fun initView() {
        with(binding) {
            menuTextView.text=menu
        }
    }
}