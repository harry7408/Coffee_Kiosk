package com.choi.coffee_kiosks.view.practice.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.base.BaseDialog
import com.choi.coffee_kiosks.databinding.FragmentSearchAndJoinBinding
import com.choi.coffee_kiosks.util.common.JOIN_KEY
import com.choi.coffee_kiosks.util.common.READ_KEY
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize
import com.choi.coffee_kiosks.viewModels.PhoneNumberViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchAndJoinFragment :
    BaseDialog<FragmentSearchAndJoinBinding>(FragmentSearchAndJoinBinding::inflate) {

    private lateinit var firstPhoneNum: EditText
    private lateinit var secondPhoneNum: EditText
    private lateinit var thirdPhoneNum: EditText

    private lateinit var joinBundle: String
    private lateinit var searchBundle: String

    private var userPhoneNumber = ""

    private val phoneNumberViewModel: PhoneNumberViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchAndJoinBinding.inflate(inflater, container, false)
        this@SearchAndJoinFragment.setWindowSize(0.8, 0.8)
        firstPhoneNum = binding.firstPhoneNumEditText
        secondPhoneNum = binding.secondPhoneNumEditText
        thirdPhoneNum = binding.thirdPhoneNumEditText
        joinBundle = arguments?.getString(JOIN_KEY, null) ?: "none"
        searchBundle = arguments?.getString(READ_KEY, null) ?: "none"
        firstPhoneNum.requestFocus()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            firstPhoneNumEditText.addTextChangedListener(
                CustomTextWatcher(
                    binding.firstPhoneNumEditText,
                    3
                )
            )
            secondPhoneNumEditText.addTextChangedListener(
                CustomTextWatcher(
                    binding.secondPhoneNumEditText,
                    4
                )
            )
            thirdPhoneNumEditText.addTextChangedListener(
                CustomTextWatcher(
                    binding.thirdPhoneNumEditText,
                    4
                )
            )

            numberOneTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberOneTextView.text.toString().toInt())
            }

            numberTwoTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberTwoTextView.text.toString().toInt())
            }

            numberThreeTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberThreeTextView.text.toString().toInt())
            }

            numberFourTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberFourTextView.text.toString().toInt())
            }

            numberFiveTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberFiveTextView.text.toString().toInt())
            }

            numberSixTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberSixTextView.text.toString().toInt())
            }

            numberSevenTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberSevenTextView.text.toString().toInt())
            }

            numberEightTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberEightTextView.text.toString().toInt())
            }

            numberNineTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberNineTextView.text.toString().toInt())
            }

            numberZeroTextView.setOnAvoidDuplicateClickWithFlow {
                addNumber(numberZeroTextView.text.toString().toInt())
            }

            backSpaceTextView.setOnAvoidDuplicateClickWithFlow {
                removeOneNumber()
            }

            // 모두 삭제
            deleteAllTextView.setOnAvoidDuplicateClickWithFlow {
                clearEditText()
            }

            nextButton.setOnAvoidDuplicateClickWithFlow {
                userPhoneNumber =
                    firstPhoneNum.text.toString() + "-" + secondPhoneNum.text.toString() + "-" + thirdPhoneNum.text.toString()
                if (joinBundle.isNotEmpty() && (searchBundle.isEmpty() || searchBundle == "none")) {
                    AlertDialog.Builder(requireContext())
                        .setMessage("회원 가입 성공")
                        .setNegativeButton(R.string.cancel_text, null)
                        .setPositiveButton(R.string.ok_text, null)
                        .create()
                        .show()

                    CoroutineScope(Dispatchers.Main).launch {
                        delay(2000L)
                        dismiss()
                    }
                } else {
                    phoneNumberViewModel.addPhoneNumber(userPhoneNumber)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(500L)
                        dismiss()
                    }
                }
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                dismiss()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addNumber(number: Int) {
        when {
            firstPhoneNum.isFocused && firstPhoneNum.text.length < 3 -> {
                firstPhoneNum.setText(firstPhoneNum.text.toString() + number.toString())
            }

            secondPhoneNum.isFocused && secondPhoneNum.text.length < 4 -> {
                secondPhoneNum.setText(secondPhoneNum.text.toString() + number.toString())
            }

            thirdPhoneNum.isFocused && thirdPhoneNum.text.length < 4 -> {
                thirdPhoneNum.setText(thirdPhoneNum.text.toString() + number.toString())
            }
        }
    }

    private fun removeOneNumber() {
        when {
            firstPhoneNum.isFocused && firstPhoneNum.text.isNotEmpty() -> {
                firstPhoneNum.setText(firstPhoneNum.text.toString().dropLast(1))
            }

            secondPhoneNum.isFocused && secondPhoneNum.text.isNotEmpty() -> {
                secondPhoneNum.setText(secondPhoneNum.text.toString().dropLast(1))
            }

            thirdPhoneNum.isFocused && thirdPhoneNum.text.isNotEmpty() -> {
                thirdPhoneNum.setText(thirdPhoneNum.text.toString().dropLast(1))
            }
        }
    }

    private fun clearEditText() {
        firstPhoneNum.setText("")
        secondPhoneNum.setText("")
        thirdPhoneNum.setText("")
        firstPhoneNum.requestFocus()
    }


    inner class CustomTextWatcher(private val editText: EditText, private val maxLength: Int) :
        TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(edt: Editable) {
            if (edt.length == maxLength) {
                when (editText) {
                    firstPhoneNum -> {
                        secondPhoneNum.requestFocus()
                    }

                    secondPhoneNum -> {
                        thirdPhoneNum.requestFocus()
                    }
                }
            }
        }
    }
}