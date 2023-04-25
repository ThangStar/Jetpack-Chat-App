package com.example.iochat.`object`

import java.text.DateFormat
import java.util.Calendar
import java.util.Date

open class Message(
    val idUserSend: String = "",
    val message: String = "",
    val target: String = "",
    val idUserGet: String = "",
)