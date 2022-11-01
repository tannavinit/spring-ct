package com.springct.data.cache

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesDB(
    private val context: Context
) {

    object Keys {
        const val TOKEN = "token"
    }

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("spring_ct_prefs", Context.MODE_PRIVATE)

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun setString(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    fun getString(key: String): String? =
        if (sharedPreferences.contains(key)) sharedPreferences.getString(key, null) else null
}