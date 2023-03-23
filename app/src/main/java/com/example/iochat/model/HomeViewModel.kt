package com.example.iochat.model
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iochat.network.ChatAPI
import com.example.iochat.`object`.User
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private var _listUser = mutableStateListOf<User>()

    val listUSer
        get() = _listUser

    init {
        getJsonFromInternet()
    }

    private fun getJsonFromInternet() {
        viewModelScope.launch {
            try {
                val data = viewModelScope.async {
                    ChatAPI.retrofitService.getListUser()
                }.await()
                _listUser.clear()
                _listUser.addAll(data)
            } catch (ex: Exception) {
                Log.d("ERROR", ex.message.toString())
            }
        }
    }
}