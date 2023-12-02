package com.choi.coffee_kiosks.model.pref

import android.content.Context
import android.content.SharedPreferences
import com.choi.coffee_kiosks.util.common.FREE_OPTIONS


class FreeOptionPreference(context: Context) {
    private val prefName = FREE_OPTIONS
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        prefName, Context.MODE_PRIVATE
    )

    fun saveData(key: String, value: String) {
        val editor = sharedPrefs.edit()
        editor.apply {
            putString(key, value)
                .apply()
        }
    }

    fun getData(key: String): String? {
        return sharedPrefs.getString(key, null)
    }

    fun clearData() {
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
    }
}