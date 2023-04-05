package com.example.iochat.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.navigate.Screen
import com.example.iochat.network.ChatAPI
import com.example.iochat.`object`.Auth
import kotlinx.coroutines.async
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Objects

class LoginViewModel : ViewModel() {
    fun checkLogin(
        username: String, password: String,
        navController: NavController,
    ) {
        viewModelScope.launch {
            try {
                val dataUser =
                    ChatAPI.retrofitService.checkLogin(Auth(username, password))
                if(dataUser.id != ""){
                    UserCurrentConfig.id = dataUser.id
                    UserCurrentConfig.avatar = dataUser.avatar
                    UserCurrentConfig.email = dataUser.email
                    UserCurrentConfig.fullname = dataUser.fullname
                    navController.navigate(Screen.HomeScreen.route)
                }else {
                    Log.d("SSS", "LOGIN FAIL");
                }
            } catch (ex: Exception) {
                Log.d("ERROR", ex.message.toString())
            }
        }
    }
}