package com.choi.coffee_kiosks.view.practice.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.entity.pref.FreeOptionPreference
import com.choi.coffee_kiosks.entity.pref.NonFreeOptionPreference
import com.choi.coffee_kiosks.entity.pref.TotalPricePreference
import com.choi.coffee_kiosks.databinding.FragmentLastDialogBinding
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize
import com.choi.coffee_kiosks.view.home.HomeFragment
import com.choi.coffee_kiosks.view.practice.result.FailureFragment
import com.choi.coffee_kiosks.view.practice.result.SuccessFragment
import com.choi.coffee_kiosks.viewmodel.MainViewModel
import com.choi.coffee_kiosks.viewmodel.PhoneNumberViewModel
import com.choi.coffee_kiosks.viewmodel.SelectedMenuViewModel

class LastDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentLastDialogBinding

    private lateinit var totalPricePreference: TotalPricePreference
    private lateinit var freeOptionPreference: FreeOptionPreference
    private lateinit var nonFreeOptionPreference: NonFreeOptionPreference


    private val mainViewModel: MainViewModel by activityViewModels()
    private val phoneNumberViewModel: PhoneNumberViewModel by activityViewModels()
    private val selectedMenuViewModel: SelectedMenuViewModel by activityViewModels()
    private lateinit var countTimer: CountDownTimer
    private lateinit var bar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        binding = FragmentLastDialogBinding.inflate(inflater, container, false)
        this.setWindowSize(0.9, 0.5)
        totalPricePreference = TotalPricePreference.getInstance(context)
        freeOptionPreference = FreeOptionPreference.getInstance(context)
        nonFreeOptionPreference = NonFreeOptionPreference.getInstance(context)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            payAmountTextView.text = totalPricePreference.getData(TOTAL_PRICE).toString() + "원"

            bar = progressBar

            okButton.setOnAvoidDuplicateClickWithFlow {
                checkAnswer()
                dismiss()
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                checkAnswer()
                dismiss()
            }
            Log.d(LOG_TAG, "ANSWER : ${mainViewModel.missionAnswer}")
            Log.d(LOG_TAG, "My Answer : ${mainViewModel.missionCourse}")

            startProgress()
        }
    }

    private fun startProgress() {
        countTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(p0: Long) {
                val progress = (p0 / 50).toInt()
                binding.progressBar.progress = progress
            }

            override fun onFinish() {
                checkAnswer()
                dismiss()
            }
        }
        countTimer.start()
    }

    private fun checkAnswer() {
        val answer = mainViewModel.missionAnswer.replace(" ", "")
        if (answer == "") {
            this.dismiss()
            parentFragment?.parentFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, HomeFragment())
                ?.commit()
            initAllData()


        } else {
            if (answer == mainViewModel.missionCourse) {
                parentFragment?.parentFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, SuccessFragment())
                    ?.commit()
                initAllData()
            } else {
                parentFragment?.parentFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, FailureFragment())
                    ?.commit()
                initAllData()
            }
        }

    }

    private fun initAllData() {
        totalPricePreference.clearData()
        freeOptionPreference.clearData()
        nonFreeOptionPreference.clearData()
        mainViewModel.missionCourse = ""
        mainViewModel.missionAnswer = ""
        phoneNumberViewModel.deleteNumber()
        selectedMenuViewModel.clearMenu()
    }
}