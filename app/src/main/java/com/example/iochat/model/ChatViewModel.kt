package com.example.iochat.model

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iochat.`object`.Message
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.socket.client.IO
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.URISyntaxException


class ChatViewModel : ViewModel() {
    val mSocket = IO.socket("http://192.168.1.13:3002")
    var _content = MutableStateFlow<Array<Message>>(emptyArray())

    val content
        get() = _content.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val deffValue = async {
                    connectToSocket()
                    getMessage()
                }.await()
            } catch (e: URISyntaxException) {
                Log.d("IO", "ERROR IO")
            }
        }
    }

    fun handleClick(message: String?) {
        mSocket.emit("message-public", message)
    }

    private suspend fun connectToSocket() {
        mSocket.connect()
    }

    suspend fun getMessage() {
        var listMessage = arrayOf<Message>()
        mSocket.on("message") { arg ->
            if (arg.size > 0) {
                val gson = Gson();
                val jsonArray = arg[0].toString()
                //array json to array object
                val type = object : TypeToken<Array<Message>>() {}.type
                listMessage = gson.fromJson<Array<Message>>(jsonArray, type)
                listMessage.forEach {
                    Log.d("SSS", it.message)
                }
                _content.value = listMessage
            }
        }
    }
}
