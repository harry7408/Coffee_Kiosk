package com.choi.coffee_kiosks.view.practice.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.choi.coffee_kiosks.viewModels.MainViewModel
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.databinding.FragmentShowOptionsBinding
import com.choi.coffee_kiosks.model.Menu
import com.choi.coffee_kiosks.model.SelectedMenu
import com.choi.coffee_kiosks.model.Type
import com.choi.coffee_kiosks.model.pref.FreeOptionPreference
import com.choi.coffee_kiosks.model.pref.NonFreeOptionPreference
import com.choi.coffee_kiosks.model.pref.TotalPricePreference
import com.choi.coffee_kiosks.util.common.CREAM_OPTIONS
import com.choi.coffee_kiosks.util.common.CREAM_PRICE
import com.choi.coffee_kiosks.util.common.DENSITY_OPTION
import com.choi.coffee_kiosks.util.common.HAZELNUT_OPTIONS
import com.choi.coffee_kiosks.util.common.HAZELNUT_PRICE
import com.choi.coffee_kiosks.util.common.ICE_OPTION
import com.choi.coffee_kiosks.util.common.LOG_TAG
import com.choi.coffee_kiosks.util.common.PERL_OPTIONS
import com.choi.coffee_kiosks.util.common.PERL_PRICE
import com.choi.coffee_kiosks.util.common.SHOT_OPTIONS
import com.choi.coffee_kiosks.util.common.SHOT_PRICE
import com.choi.coffee_kiosks.util.common.SUGAR_OPTION
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE
import com.choi.coffee_kiosks.util.common.VANILLA_OPTIONS
import com.choi.coffee_kiosks.util.common.VANILLA_PRICE
import com.choi.coffee_kiosks.util.common.setOnAvoidDuplicateClickWithFlow
import com.choi.coffee_kiosks.util.common.setWindowSize
import com.choi.coffee_kiosks.view.practice.options.AdeOptionFragment
import com.choi.coffee_kiosks.view.practice.options.CoffeeOptionFragment
import com.choi.coffee_kiosks.view.practice.options.JuiceOptionFragment
import com.choi.coffee_kiosks.view.practice.options.NonCoffeeOptionFragment
import com.choi.coffee_kiosks.view.practice.options.TeaOptionFragment
import com.choi.coffee_kiosks.viewModels.SelectedMenuViewModel
import com.choi.coffee_kiosks.viewModels.TotalPriceViewModel

class ShowOptionsFragment(private val menu: Menu) :
    DialogFragment() {
    private lateinit var binding: FragmentShowOptionsBinding
    private var currentCount = 1

    private var freeOptionsInformation = ""
    private var nonFreeOptionsInformation = ""
    private lateinit var freePreference: FreeOptionPreference
    private lateinit var nonFreePreference: NonFreeOptionPreference
    private lateinit var totalPricePreference: TotalPricePreference

    private val viewModel: MainViewModel by activityViewModels()
    private val selectedViewModel: SelectedMenuViewModel by activityViewModels()

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
        freePreference = FreeOptionPreference.getInstance(context)
        nonFreePreference = NonFreeOptionPreference.getInstance(context)
        totalPricePreference = TotalPricePreference.getInstance(context)
        initView()

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
                val menuAndCount =
                    binding.menuTextView.text.toString() + binding.countTextView.text.toString() + "잔"

                freeOptionsInformation = freePreference.run {
                    ((getData(SUGAR_OPTION) ?: "") + (getData(ICE_OPTION) ?: "") + (getData(
                        DENSITY_OPTION
                    ) ?: ""))
                }.toString()

                nonFreeOptionsInformation = nonFreePreference.run {
                    ((((getData(HAZELNUT_OPTIONS) ?: "") + (getData(PERL_OPTIONS)
                        ?: "")) + (getData(VANILLA_OPTIONS) ?: "")) + (getData(SHOT_OPTIONS)
                        ?: "")) + (getData(CREAM_OPTIONS) ?: "")
                }.toString()

                // 선택한 메뉴 보내기
                viewModel.missionCourse += (menuAndCount + freeOptionsInformation + nonFreeOptionsInformation)


                Log.d(LOG_TAG, "FREE OPTIONS : $freeOptionsInformation")
                Log.d(LOG_TAG, "NONFREE OPTIONS : $nonFreeOptionsInformation")
                Log.d(LOG_TAG, "COURSE : ${viewModel.missionCourse}")

                val menuPrice = binding.amountTextView.text.toString().run {
                    this.subSequence(0, this.length - 2).toString().toInt()
                }

                val hazelNutOptionPrice = (nonFreePreference.getData(HAZELNUT_PRICE) ?: "").run {
                    if (this == "") {
                        0
                    } else {
                        this.toInt()
                    }
                }

                val perlOptionPrice = (nonFreePreference.getData(PERL_PRICE) ?: "").run {
                    if (this == "") {
                        0
                    } else {
                        this.toInt()
                    }
                }

                val creamOptionPrice = (nonFreePreference.getData(CREAM_PRICE) ?: "").run {
                    if (this == "") {
                        0
                    } else {
                        this.toInt()
                    }
                }

                val vanillaOptionPrice = (nonFreePreference.getData(VANILLA_PRICE) ?: "").run {
                    if (this == "") {
                        0
                    } else {
                        this.toInt()
                    }
                }

                val shotOptionPrice = (nonFreePreference.getData(SHOT_PRICE) ?: "").run {
                    if (this == "") {
                        0
                    } else {
                        this.toInt()
                    }
                }

                var totalPrice = totalPricePreference.getData(TOTAL_PRICE)
                totalPrice += (menuPrice + hazelNutOptionPrice +
                        perlOptionPrice + creamOptionPrice + vanillaOptionPrice + shotOptionPrice)

                totalPricePreference.saveData(TOTAL_PRICE, totalPrice)

                val currentMenuPrice =
                    menuPrice + hazelNutOptionPrice + perlOptionPrice + creamOptionPrice
                +vanillaOptionPrice + shotOptionPrice

                Log.d(LOG_TAG, "menu price : $menuPrice")
                Log.d(LOG_TAG, "total price : $totalPrice")
                //Todo Bottom Dialog에 데이터 보내기

                val selectedMenu = SelectedMenu(
                    image = menu.image,
                    price = currentMenuPrice,
                    count = binding.countTextView.text.toString().toInt()
                )

                selectedViewModel.addSelectedMenu(selectedMenu)
                freePreference.clearData()
                nonFreePreference.clearData()
//                totalPricePreference.clearData()
                dismiss()
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
                // 다른 메뉴로 넘어갈 때 sharedPreference를 초기화 해주기 위함
                freePreference.clearData()
                nonFreePreference.clearData()
                totalPricePreference.clearData()

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

