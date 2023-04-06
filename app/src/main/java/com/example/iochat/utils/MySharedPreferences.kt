package com.example.iochat.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class MySharedPreferences(
    val context: Context,
) {
    fun getSharedPref(): SharedPreferences {
        val sharedPref: SharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        //get Editor
        return sharedPref
    }

    fun putString(key: String, value: String, sharedPref: SharedPreferences) {
        try {
            val myEditer = sharedPref.edit()
            myEditer.putString(key, value)
            myEditer.apply()
        } catch (ex: Exception) {
            Log.d("SSS", "PUT VALUE ERROR: $ex")
        }
    }
    fun getString(key: String, defaultValue: String, sharedPref: SharedPreferences): String {
        return sharedPref.getString(key, defaultValue).toString()
    }
}