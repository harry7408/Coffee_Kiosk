package com.choi.coffee_kiosks.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.choi.coffee_kiosks.model.SelectedMenu

class SelectedMenuViewModel: ViewModel() {

    private val _selectedMenus = MutableLiveData<List<SelectedMenu>>(emptyList())
    val selectedMenus: LiveData<List<SelectedMenu>> = _selectedMenus

    fun addSelectedMenu(menu: SelectedMenu) {
        val currentMenus = _selectedMenus.value ?: emptyList()
        _selectedMenus.value = currentMenus + menu
    }
}