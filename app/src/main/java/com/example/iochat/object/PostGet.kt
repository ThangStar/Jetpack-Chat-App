package com.example.iochat.`object`

import java.util.Date

data class PostGet(
    val _id: String = "",
    val idUser: String = "",
    val content: String = "",
    val time: Date,
    val image: String = ""
)