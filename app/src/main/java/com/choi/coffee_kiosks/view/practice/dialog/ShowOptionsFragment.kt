package com.choi.coffee_kiosks.view.practice.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.databinding.FragmentShowOptionsBinding
import com.choi.coffee_kiosks.model.Menu
import com.choi.coffee_kiosks.model.Type
import com.choi.coffee_kiosks.model.pref.FreeOptionPreference
import com.choi.coffee_kiosks.model.pref.NonFreeOptionPreference
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize
import com.choi.coffee_kiosks.view.practice.options.AdeOptionFragment
import com.choi.coffee_kiosks.view.practice.options.CoffeeOptionFragment
import com.choi.coffee_kiosks.view.practice.options.JuiceOptionFragment
import com.choi.coffee_kiosks.view.practice.options.NonCoffeeOptionFragment
import com.choi.coffee_kiosks.view.practice.options.TeaOptionFragment

class ShowOptionsFragment(private val menu: Menu) :
    DialogFragment() {
    private lateinit var binding: FragmentShowOptionsBinding
    private var currentCount = 1

    private var freeOptionsInformation = ""
    private var nonFreeOptionsInformation = ""
    private lateinit var freePreference: FreeOptionPreference
    private lateinit var nonFreePreference: NonFreeOptionPreference

//    val viewModel: MainViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val context = requireContext()
        binding = FragmentShowOptionsBinding.inflate(inflater, container, false)
        val view = binding.root

        this@ShowOptionsFragment.setWindowSize(0.9, 0.9)
        initView()
        freePreference = FreeOptionPreference.getInstance(context)
        nonFreePreference = NonFreeOptionPreference.getInstance(context)
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            coldButton.apply {
                setOnAvoidDuplicateClickWithFlow {

                    menuTextView.text = "${menu.name}(Ice)"
                    setBackgroundResource(R.drawable.background_text_choose)
                }
            }

            hotButton.setOnAvoidDuplicateClickWithFlow {
                menuTextView.text = "${menu.name}(Hot)"
            }

            plusImageView.setOnAvoidDuplicateClickWithFlow {
                currentCount++
                countTextView.text = currentCount.toString()
                amountTextView.text = "${menu.price * currentCount} 원"
            }

            minusImageView.setOnAvoidDuplicateClickWithFlow {
                currentCount--
                if (currentCount == 0) {
                    currentCount++
                }
                countTextView.text = currentCount.toString()
                amountTextView.text = "${menu.price * currentCount} 원"
            }

            okButton.setOnAvoidDuplicateClickWithFlow {
                //todo 데이터 전달 (무료옵션, 유료옵션 -> SharedPreference에서 가져오기)


            }

            addFreeTextView.setOnAvoidDuplicateClickWithFlow {
                val dialog = FreeOptionFragment(binding.menuTextView.text.toString())
                dialog.isCancelable = true
                dialog.show(childFragmentManager, null)
            }

            addPayTextView.setOnAvoidDuplicateClickWithFlow {
                val menuName = menu.name
                val dialogFragment = when (menu.type) {
                    Type.ADE -> AdeOptionFragment(menuName)
                    Type.COFFEE -> CoffeeOptionFragment(menuName)
                    Type.NON_COFFEE -> NonCoffeeOptionFragment(menuName)
                    Type.JUICE -> JuiceOptionFragment(menuName)
                    else -> TeaOptionFragment(menuName)
                }
                dialogFragment.isCancelable = true
                dialogFragment.show(childFragmentManager, null)
            }

            cancelButton.setOnAvoidDuplicateClickWithFlow {
                freePreference.clearData()
                nonFreePreference.clearData()
                dismiss()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        with(binding) {
            when (menu.type) {
                Type.ADE, Type.JUICE -> {
                    hotButton.visibility = View.GONE
                    coldButton.visibility = View.GONE
                    icedOnlyButton.visibility = View.VISIBLE
                    menuTextView.text = "${menu.name}(Ice)"
                    countTextView.text = currentCount.toString()
                    amountTextView.text = "${menu.price} 원"
                    menuImageView.setImageResource(menu.image)
                }

                Type.DESSERT -> {
                    hotButton.visibility = View.GONE
                    coldButton.visibility = View.GONE
                    icedOnlyButton.visibility = View.GONE
                    payOptionsTextView.visibility = View.GONE
                    addPayTextView.visibility = View.GONE
                    line1.visibility = View.GONE
                    freeOptionsTextView.visibility = View.GONE
                    addFreeTextView.visibility = View.GONE
                    line2.visibility = View.GONE
                    menuTextView.text = menu.name
                    countTextView.text = currentCount.toString()
                    amountTextView.text = "${menu.price} 원"
                    menuImageView.setImageResource(menu.image)
                }

                else -> {
                    hotButton.visibility = View.VISIBLE
                    coldButton.visibility = View.VISIBLE
                    icedOnlyButton.visibility = View.GONE
                    menuTextView.text = menu.name
                    countTextView.text = currentCount.toString()
                    amountTextView.text = "${menu.price} 원"
                    menuImageView.setImageResource(menu.image)
                }
            }
        }
    }
}

