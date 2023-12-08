package com.choi.coffee_kiosks.view.practice.result

import android.os.Bundle
import android.view.View
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentFailureBinding
import com.choi.coffee_kiosks.view.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FailureFragment: BaseFragment<FragmentFailureBinding>(FragmentFailureBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,HomeFragment())
                .commit()
        }
    }

}