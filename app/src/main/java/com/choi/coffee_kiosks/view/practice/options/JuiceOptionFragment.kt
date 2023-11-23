package com.choi.coffee_kiosks.view.practice.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentJuiceOptionBinding
import com.choi.coffee_kiosks.util.common.setWindowSize

class JuiceOptionFragment(private val menu : String) :
    BaseDialog<FragmentJuiceOptionBinding>(FragmentJuiceOptionBinding::inflate) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJuiceOptionBinding.inflate(inflater, container, false)
        this@JuiceOptionFragment.setWindowSize(0.85, 0.85)
        initView()
        return binding.root
    }



    private fun initView() {
        with(binding) {
            menuTextView.text=menu
        }
    }
}