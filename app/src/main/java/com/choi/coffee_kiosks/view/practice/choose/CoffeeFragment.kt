package com.choi.coffee_kiosks.view.practice.choose

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.choi.coffee_kiosks.adapter.MenuAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentCoffeeBinding
import com.choi.coffee_kiosks.util.common.coffees
import com.choi.coffee_kiosks.util.common.showToastMessage

class CoffeeFragment: BaseFragment<FragmentCoffeeBinding>(FragmentCoffeeBinding::inflate) {
    private lateinit var adapter : MenuAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            coffeeRecyclerView.apply {
                layoutManager=GridLayoutManager(requireContext(),2)
                adapter=MenuAdapter(coffees) {

                }
            }
        }
    }


}