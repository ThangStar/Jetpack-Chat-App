package com.example.iochat.service

import android.app.Notification.Action.SEMANTIC_ACTION_ARCHIVE
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.semantics.SemanticsActions
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat.getSystemService
import com.example.iochat.R
import com.example.iochat.screens.ChatAppScreen

class NotificationService(
    private val context: Context,
) {
    // Key for the string that's delivered in the action's intent.
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(title: String, subTitle: String) {

        val KEY_TEXT_REPLY = "key_text_reply"
        var replyLabel: String = "Nhập tin nhắn.."
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }

        var replyPendingIntent: PendingIntent =
            PendingIntent.getBroadcast(
                context,
                1,
                Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        var action: NotificationCompat.Action =
            NotificationCompat.Action.Builder(
                R.drawable.round_send_24,
                "Trả lời", replyPendingIntent
            )
                .addRemoteInput(remoteInput)
                .build()

        var actionDismiss: NotificationCompat.Action =
            NotificationCompat.Action.Builder(
                R.drawable.round_send_24,
                "Tắt thông báo", replyPendingIntent
            )
                .setSemanticAction(NotificationCompat.Action.SEMANTIC_ACTION_CALL)
                .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.round_mark_chat_unread_24)
                .setContentTitle(title)
                .setContentText(subTitle)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .addAction(action)
                .addAction(actionDismiss)
                .build()
            val channel =
                NotificationChannel(CHANNEL_ID, "987", NotificationManager.IMPORTANCE_HIGH).apply {
                    description = "descriptionText"
                }
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(1, notification)
        }
    }

    companion object {
        const val CHANNEL_ID = "ID_CHANNEL"
    }
}