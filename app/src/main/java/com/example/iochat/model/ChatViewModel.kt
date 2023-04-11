package com.example.iochat.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iochat.config.APIConfig
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.network.ChatAPI
import com.example.iochat.`object`.Message
import com.example.iochat.service.NotificationService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.socket.client.IO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URISyntaxException
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : ViewModel() {
    val mSocket = IO.socket(APIConfig.APISocketIO).connect()
    var _content = MutableStateFlow<Array<Message>>(emptyArray())

    val content
        get() = _content.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    mSocket.emit("initIdDb", UserCurrentConfig.id)
                    initDataMessage() //from retrofit
                    mSocket.emit("room-broadcast", UserCurrentConfig.idUserChating)
                    reloadMessage(context, "message-broadcast")
                }

            } catch (e: URISyntaxException) {
                Log.d("IO", "ERROR IO")
            }
        }
    }

    fun handleClick(message: String) {
        viewModelScope.launch {
            val objectMessage =
                Message(UserCurrentConfig.id, message, "broadcast", UserCurrentConfig.idUserChating)
            val jsonObjectMessage = Gson().toJson(objectMessage)
            mSocket.emit("add-message", jsonObjectMessage)
            _content.value += Message(
                UserCurrentConfig.id,
                message,
                "",
                UserCurrentConfig.idUserChating
            )

        }
    }

    private suspend fun reloadMessage(context: Context, event: String) {
        var listMessage = arrayOf<Message>()
        mSocket.on(event) { arg ->
            Log.d("SSS ARG: ", arg.toString())
            if (arg.isNotEmpty()) {
                val gson = Gson();
                val jsonArray = arg[0].toString()
                //array json to array object
                val type = object : TypeToken<Array<Message>>() {}.type
                listMessage = gson.fromJson<Array<Message>>(jsonArray, type)
                Log.d("SSS SIZE MESSAGE", "${listMessage.size}")
                listMessage.map {
                    Log.d("SSS CONTENT ", it.message);
                    Log.d("SSS CONTENT ", it.idUserGet);
                    Log.d("SSS CONTENT ", it.idUserSend);
                    Log.d("SSS CONTENT ", it.target);
                }
                if (listMessage[0].idUserSend == UserCurrentConfig.idUserChating) {
                    _content.value += listMessage
                } else {
                    // send a nofication
                    NotificationService(context).showNotification(
                        "Tin nhắn mới",
                        listMessage[0].message
                    )
                }
              
            }
        }
    }

    private suspend fun initDataMessage() {
        try {
            val data =
                ChatAPI.retrofitService.getMessageRoomBroadCast(
                    Message(
                        idUserSend = UserCurrentConfig.id,
                        idUserGet = UserCurrentConfig.idUserChating, message = "", target = ""
                    )
                )
            _content.value = data
        } catch (ex: Exception) {
            Log.d("ERROR", ex.message.toString())
        }
    }

    fun disConnect() {
        Log.d("SSS", "BACK DISCONNECT")
        mSocket.disconnect()
    }
}
