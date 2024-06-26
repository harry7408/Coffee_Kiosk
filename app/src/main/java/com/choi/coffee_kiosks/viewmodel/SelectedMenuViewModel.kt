package com.choi.coffee_kiosks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.choi.coffee_kiosks.entity.SelectedMenu

class SelectedMenuViewModel() : ViewModel() {

    private val _selectedMenus = MutableLiveData<MutableList<SelectedMenu>>(mutableListOf())
    val selectedMenus: LiveData<MutableList<SelectedMenu>> = _selectedMenus

    fun addSelectedMenu(menu: SelectedMenu) {
        val currentMenus = _selectedMenus.value ?: mutableListOf()
        _selectedMenus.value = (currentMenus + menu).toMutableList()
    }

    fun clearMenu() {
        _selectedMenus.value?.clear()
        _selectedMenus.value = _selectedMenus.value
    }
}