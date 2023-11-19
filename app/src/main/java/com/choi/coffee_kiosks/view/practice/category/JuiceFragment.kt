package com.choi.coffee_kiosks.view.practice.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.choi.coffee_kiosks.adapter.MenuAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentJuiceBinding
import com.choi.coffee_kiosks.util.common.juices

class JuiceFragment : BaseFragment<FragmentJuiceBinding>(FragmentJuiceBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            juiceRecyclerView.apply {
                layoutManager= GridLayoutManager(requireContext(),2)
                adapter= MenuAdapter(juices) { name,price,imgId ->

                }
            }
        }
    }

}