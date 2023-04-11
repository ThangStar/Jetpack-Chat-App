package com.example.iochat.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.migration.CustomInject
import io.socket.client.Socket
import javax.inject.Inject

@HiltAndroidApp
class ChatApp: Application(){

}

