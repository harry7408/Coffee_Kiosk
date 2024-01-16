package com.choi.coffee_kiosks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.choi.coffee_kiosks.entity.KioskPosition
import com.choi.coffee_kiosks.repository.PlaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaceViewModel(private val placeRepository: PlaceRepository) : ViewModel() {

    // stateFlow는 처음에 값을 채워줘야 한다
    private val _placeInfo= MutableStateFlow<List<KioskPosition>>(emptyList())
    val palceInfo : StateFlow<List<KioskPosition>> = _placeInfo


    private fun getKioskPlace() = viewModelScope.launch {
        _placeInfo.value=placeRepository.getPlaces().data
    }

    init {
        getKioskPlace()
    }

    /**
     * Place view model factory
     * ViewModel 생성을 위한 Factory (팩토리 패턴)
     * @property placeRepository
     * @constructor Create empty Place view model factory
     */
    class PlaceViewModelFactory(private val placeRepository: PlaceRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlaceViewModel(placeRepository) as T
        }
    }
}