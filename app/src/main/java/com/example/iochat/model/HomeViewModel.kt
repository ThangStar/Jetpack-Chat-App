package com.example.iochat.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.network.ChatAPI
import com.example.iochat.`object`.Message
import com.example.iochat.`object`.User
import com.example.iochat.`object`.UserCurrent
import com.example.iochat.service.NotificationService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.socket.client.Socket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import retrofit2.Retrofit
import java.util.Objects
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {
    private var _listUser = MutableStateFlow<Array<User>>(emptyArray())
    private var _arrResultSearchFriend = MutableStateFlow<Array<User>>(emptyArray())

    val listUSer
        get() = _listUser.asStateFlow()

    val arrResultSearchFriend
        get() = _arrResultSearchFriend.asStateFlow()
    init {
        reloadListUser()
    }

    private fun reloadListUser() {
        viewModelScope.launch {
            try {
                val data = viewModelScope.async {
                    ChatAPI.retrofitService.getListUser(UserCurrent(id = UserCurrentConfig.id))
                }.await()
                _listUser.value = data
            } catch (ex: Exception) {
                Log.d("ERROR", ex.message.toString())
            }
        }
    }

    public fun searchByKeyWord(keyWord: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if(keyWord.isNotBlank()){
                    val arrUser = ChatAPI.retrofitService.searchByNameOrEmail(keyWord)
                    _arrResultSearchFriend.value = arrUser
                }else {
                    _arrResultSearchFriend.value = emptyArray()
                }
            }
        }
    }

}

