package com.choi.coffee_kiosks.view.practice.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentSearchAndJoinBinding
import com.choi.coffee_kiosks.util.common.setWindowSize

class SearchAndJoinFragment :
    BaseDialog<FragmentSearchAndJoinBinding>(FragmentSearchAndJoinBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentSearchAndJoinBinding.inflate(inflater,container,false)
        this@SearchAndJoinFragment.setWindowSize(0.8,0.8)
        return binding.root
    }

}