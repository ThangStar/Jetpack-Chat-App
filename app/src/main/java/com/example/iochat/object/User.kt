package com.example.iochat.`object`

import com.google.gson.annotations.SerializedName

open class User(
    val _id: String,
    @SerializedName("fullname")
    val fullName: String,
    val avatar: String,
)