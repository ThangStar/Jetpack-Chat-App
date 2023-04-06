package com.example.iochat.model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.network.ChatAPI
import com.example.iochat.`object`.User
import com.example.iochat.`object`.UserCurrent
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Objects
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(age: Int): ViewModel() {
    private var _listUser = MutableStateFlow<Array<User>>(emptyArray())

    val listUSer
        get() = _listUser.asStateFlow()

    init {
        Log.d("SSS", "HOME VIEW MODEL CALLing params is.."+age)
        reloadListUser()
    }

    private fun reloadListUser() {
        viewModelScope.launch {
            try {
                val data = viewModelScope.async {
                    ChatAPI.retrofitService.getListUser(UserCurrent(id = UserCurrentConfig.id))
                }.await()
                Log.d("SSS", "" + data.size)
                _listUser.value = data
            } catch (ex: Exception) {
                Log.d("ERROR", ex.message.toString())
            }
        }
    }
}

