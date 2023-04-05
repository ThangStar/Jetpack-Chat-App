package com.example.iochat.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class MySharedPreferences(
    val context: Context,
) {
    fun getSharedPref(): SharedPreferences {
        val sharedPref: SharedPreferences = context.getSharedPreferences("Auth", 0)
        //get Editor
        return sharedPref
    }

    fun putString(key: String, value: String, sharedPref: SharedPreferences) {
        try {
            val myEditer = sharedPref.edit()
            myEditer.putString(key, value)
        } catch (ex: Exception) {
            Log.d("SSS", "PUT VALUE ERROR: $ex")
        }
    }
    fun getString(key: String, valueIfError: String, sharedPref: SharedPreferences): String? {
        return sharedPref.getString(key, valueIfError)
    }
}