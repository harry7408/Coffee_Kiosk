package com.choi.coffee_kiosks.util.common

import android.content.Context
import android.widget.Toast
import com.shashank.sony.fancytoastlib.FancyToast

fun Context.showToastMessage(message: String) {
    FancyToast.makeText(this,message,FancyToast.LENGTH_SHORT,
        FancyToast.WARNING,false)
}

