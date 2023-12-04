package com.choi.coffee_kiosks.viewModels

import androidx.lifecycle.ViewModel
import com.choi.coffee_kiosks.util.common.AppUtil

class MainViewModel : ViewModel() {
    var missionAnswer: String = ""
    var missionCourse: String = ""
    var userUUID: String = ""
    var userPhoneNumber: String = ""
}