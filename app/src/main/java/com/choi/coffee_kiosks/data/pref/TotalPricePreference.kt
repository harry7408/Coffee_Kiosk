package com.choi.coffee_kiosks.data.pref

import android.content.Context
import android.content.SharedPreferences
import com.choi.coffee_kiosks.util.common.TOTAL_PRICE_SK

class TotalPricePreference private constructor(context: Context) {
    private val prefName = TOTAL_PRICE_SK

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(
        prefName, Context.MODE_PRIVATE
    )

    fun saveData(key: String, value: Int) {
        val editor = sharedPrefs.edit()
        editor.apply {
            putInt(key, value)
                .apply()
        }
    }

    fun getData(key: String): Int {
        return sharedPrefs.getInt(key, 0)
    }

    fun clearData() {
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        @Volatile
        private var INSTANCE: TotalPricePreference?=null

        fun getInstance(context: Context) : TotalPricePreference {
            return INSTANCE ?: synchronized(this) {
                val instance= TotalPricePreference(context)
                INSTANCE=instance
                instance
            }
        }
    }

}