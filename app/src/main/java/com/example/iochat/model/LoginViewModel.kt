package com.example.iochat.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.iochat.config.ModuleConfig
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.navigate.Screen
import com.example.iochat.network.ChatAPI
import com.example.iochat.`object`.Auth
import com.example.iochat.utils.MySharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
   @ApplicationContext val context: Context,
) : ViewModel() {
    private val myShare = MySharedPreferences(context).getSharedPref()

    fun getUsernameDefault(): String {
        return MySharedPreferences(context).getString(
            ModuleConfig.KEY_USERNAME,
            "",
            myShare
        )
    }

    fun getPasswordDefault(): String {
        return MySharedPreferences(context).getString(
            ModuleConfig.KEY_PASSWORD,
            "",
            myShare
        )
    }

    fun checkLogin(
        username: String, password: String,
        navController: NavController,
    ) {
        viewModelScope.launch {
            try {
                val dataUser =
                    ChatAPI.retrofitService.checkLogin(Auth(username, password))
                if (dataUser.id != "") {
                    UserCurrentConfig.id = dataUser.id
                    UserCurrentConfig.avatar = dataUser.avatar
                    UserCurrentConfig.email = dataUser.email
                    UserCurrentConfig.fullname = dataUser.fullname
                    navController.navigate(Screen.HomeScreen.route)

                    //save to ref
                    MySharedPreferences(context).putString(ModuleConfig.KEY_USERNAME, username, myShare)
                    MySharedPreferences(context).putString(ModuleConfig.KEY_PASSWORD, password, myShare)
                } else {
                    Log.d("SSS", "LOGIN FAIL");
                }
            } catch (ex: Exception) {
                Log.d("ERROR", ex.message.toString())
            }
        }
    }
}