package com.choi.coffee_kiosks.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhoneNumberViewModel : ViewModel() {
    private var _phoneNumber= MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    fun addPhoneNumber(data : String) {
        _phoneNumber.value=data
    }

    fun deleteNumber() {
        _phoneNumber.value=""
    }
}