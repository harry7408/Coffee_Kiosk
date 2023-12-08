package com.choi.coffee_kiosks.view.practice.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.databinding.FragmentRequestCardBinding
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize
import com.choi.coffee_kiosks.view.practice.charge.OrderFinishFragment

class RequestCardFragment : DialogFragment() {
    private lateinit var binding : FragmentRequestCardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentRequestCardBinding.inflate(inflater,container,false)
        this.setWindowSize(0.9,0.9)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            inputCardView.setOnAvoidDuplicateClickWithFlow {
                // TODO 주문완료 화면 띄우기
                val dialog=OrderFinishFragment()
                dialog.isCancelable=true
                dialog.show(parentFragmentManager,null)

                this@RequestCardFragment.dismiss()
            }
        }
    }
}