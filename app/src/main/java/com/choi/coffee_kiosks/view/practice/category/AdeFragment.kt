package com.choi.coffee_kiosks.view.practice.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.choi.coffee_kiosks.adapter.MenuAdapter
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentAdeBinding
import com.choi.coffee_kiosks.util.common.OPTIONS
import com.choi.coffee_kiosks.util.common.ades
import com.choi.coffee_kiosks.view.practice.dialog.ShowOptionsFragment

class AdeFragment : BaseFragment<FragmentAdeBinding>(FragmentAdeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            adeRecyclerView.apply {
                layoutManager= GridLayoutManager(requireContext(),2)
                adapter= MenuAdapter(ades) { menu ->
                    val dialog= ShowOptionsFragment(menu)
                    dialog.isCancelable=true
                    dialog.show(childFragmentManager, OPTIONS)
                }
            }
        }
    }


}