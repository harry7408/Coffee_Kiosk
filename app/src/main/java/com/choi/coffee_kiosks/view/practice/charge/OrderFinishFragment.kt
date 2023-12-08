package com.choi.coffee_kiosks.view.practice.charge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.base.BaseFragment
import com.choi.coffee_kiosks.databinding.FragmentOrderFinishBinding
import com.choi.coffee_kiosks.util.common.setWindowSize
import com.choi.coffee_kiosks.view.practice.dialog.LastDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OrderFinishFragment : DialogFragment() {
    private lateinit var binding: FragmentOrderFinishBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOrderFinishBinding.inflate(inflater,container,false)
        this.setWindowSize(0.8,0.8)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       showLast()
    }

    private fun showLast() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000L)
            val dialog=LastDialogFragment()
            dialog.isCancelable=true
            dialog.show(parentFragmentManager,null)

            CoroutineScope(Dispatchers.Main).launch {
                delay(3000L)
                dismiss()
            }
        }
    }

}