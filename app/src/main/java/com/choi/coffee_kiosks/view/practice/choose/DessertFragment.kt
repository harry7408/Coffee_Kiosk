package com.choi.coffee_kiosks.view.practice.choose

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.choi.coffee_kiosks.adapter.MenuAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentDessertBinding
import com.choi.coffee_kiosks.util.common.coffees
import com.choi.coffee_kiosks.util.common.desserts

class DessertFragment: BaseFragment<FragmentDessertBinding>(FragmentDessertBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            dessertRecyclerView.apply {
                layoutManager= GridLayoutManager(requireContext(),2)
                adapter= MenuAdapter(desserts) {

                }
            }
        }
    }
}