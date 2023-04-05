package com.example.iochat.`object`

import com.google.gson.annotations.SerializedName

 class UserCurrent (
    @SerializedName("_id")
    val id: String = "",
    val fullname: String = "",
    val avatar: String = "",
    val email: String = ""
)