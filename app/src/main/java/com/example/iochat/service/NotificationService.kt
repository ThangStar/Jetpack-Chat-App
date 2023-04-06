package com.example.iochat.service

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.iochat.R

class NotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    fun showNotification(title: String, subTitle: String){
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.round_mark_chat_unread_24)
            .setContentTitle(title)
            .setContentText(subTitle)
            .build()
        notificationManager.notify(1,notification)
    }

    companion object {
        const val CHANNEL_ID = "ID_CHANNEL"
    }
}