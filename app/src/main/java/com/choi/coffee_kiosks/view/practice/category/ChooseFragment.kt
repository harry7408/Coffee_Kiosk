package com.choi.coffee_kiosks.view.practice.category

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentChooseBinding
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.changeMenu
import com.choi.coffee_kiosks.view.practice.dialog.BottomGuideFragment
import com.google.android.material.textview.MaterialTextView

class ChooseFragment : BaseFragment<FragmentChooseBinding>(FragmentChooseBinding::inflate) {

    private var categoryMap = mapOf<MaterialTextView, Fragment>()

    private var focusedCategory: MaterialTextView? = null
    private lateinit var currentFragment: Fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryMap = mapOf(
            binding.coffeeTextView to CoffeeFragment(),
            binding.teaTextView to TeaFragment(),
            binding.adeTextView to AdeFragment(),
            binding.nonCoffeeTextView to NoneCoffeeFragment(),
            binding.juiceTextView to JuiceFragment(),
            binding.dessertTextView to DessertFragment(),

            )
        with(binding) {
            focusedCategory = binding.coffeeTextView
            currentFragment = CoffeeFragment()
            coffeeTextView.setBackgroundResource(R.drawable.background_text_choose)
            categoryFragmentContainerView.changeFragment(
                this@ChooseFragment,
                currentFragment
            )

            binding.kioskBottomSheet.fragmentContainer.apply {
                childFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, BottomGuideFragment())
                    .commit()
            }

        }
        changeCheckedCategory()
    }

    @SuppressLint("ResourceAsColor")
    private fun changeCheckedCategory() {
        for (selected in categoryMap.keys) {
            selected.onAvoidDuplicateClick {
                focusedCategory?.setBackgroundResource(R.drawable.background_not_choose)
                focusedCategory?.setTextColor(Color.BLACK)

                selected.apply {
                    selected.setBackgroundResource(R.drawable.background_text_choose)
                    setTextColor(Color.WHITE)
                }

                focusedCategory = selected

                val fragment = categoryMap[selected]

                binding.categoryFragmentContainerView.changeMenu(
                    currentFragment, fragment!!
                )
                currentFragment = fragment
            }
        }
    }
}
