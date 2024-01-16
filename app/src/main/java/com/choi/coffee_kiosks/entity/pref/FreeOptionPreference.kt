package com.choi.coffee_kiosks.entity.pref

import android.content.Context
import android.content.SharedPreferences
import com.choi.coffee_kiosks.util.common.FREE_OPTIONS


class FreeOptionPreference private constructor(context: Context) {
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

    companion object {
        @Volatile
        private var INSTANCE: FreeOptionPreference?=null

        fun getInstance(context: Context) : FreeOptionPreference {
            return INSTANCE ?: synchronized(this) {
                val instance=FreeOptionPreference(context)
                INSTANCE=instance
                instance
            }
        }
    }
}