package com.choi.coffee_kiosks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.choi.coffee_kiosks.entity.pref.TotalPricePreference
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE

class TotalPriceViewModel(private val totalPricePreference: TotalPricePreference) : ViewModel() {
    private val _totalAmount= MutableLiveData<Int>()
    val totalAmount: LiveData<Int> = _totalAmount

    init {
        _totalAmount.value=totalPricePreference.getData(TOTAL_PRICE)
    }


    fun updateTotalAmount(totalPrice: Int) {
        _totalAmount.value = totalPrice
    }

    fun subtractAmount(price: Int) {
        _totalAmount.value=(_totalAmount.value ?: 0) - price
    }

    fun clearPrice() {
        _totalAmount.value=0
        totalPricePreference.saveData(TOTAL_PRICE,0)
    }
}