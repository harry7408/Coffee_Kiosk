package com.choi.coffee_kiosks.view.practice.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.choi.coffee_kiosks.MainViewModel
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentKioskMainBinding
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.util.common.changeFragment
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.view.practice.category.ChooseFragment

class KioskMainFragment :
    BaseFragment<FragmentKioskMainBinding>(FragmentKioskMainBinding::inflate) {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentKioskMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            backFloatingActionButton.onAvoidDuplicateClick {
                parentFragmentManager.popBackStack()
            }

            inStoreButton.onAvoidDuplicateClick {
                binding.mainLayout.changeFragment(
                    this@KioskMainFragment,
                    ChooseFragment()
                )
            }

            takeoutButton.setOnAvoidDuplicateClickWithFlow {
                binding.mainLayout.changeFragment(
                    this@KioskMainFragment,
                    ChooseFragment()
                )
            }

            Log.d(LOG_TAG,mainViewModel.missionAnswer)
        }
    }
}