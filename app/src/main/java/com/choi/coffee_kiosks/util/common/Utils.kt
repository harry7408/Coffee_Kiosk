package com.choi.coffee_kiosks.util.common

import android.content.Context
import com.shashank.sony.fancytoastlib.FancyToast
import java.util.UUID

fun Context.showToastMessage(message: String) {
    FancyToast.makeText(this,message,FancyToast.LENGTH_SHORT,
        FancyToast.WARNING,false)
}

/**
 * App util
 * 사용자의 UUID를 발급 받기 위해 만든 Class
 */
class AppUtil {
    companion object {
        private const val PREFS_FILE = "AppPreference"
        private const val PREF_UUID = "PREF_UUID"

        fun getUUID(context: Context): String {
            val sharedPrefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
            var uuid = sharedPrefs?.getString(PREF_UUID, null)

            if (uuid == null) {
                uuid = UUID.randomUUID().toString()
                val editor = sharedPrefs?.edit()
                editor?.putString(PREF_UUID, uuid)
                editor?.apply()
            }

            return uuid ?: ""
        }
    }
}
