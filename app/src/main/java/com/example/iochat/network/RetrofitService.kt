package com.example.iochat.network

import com.example.iochat.config.APIConfig
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.`object`.Auth
import com.example.iochat.`object`.Message
import com.example.iochat.`object`.User
import com.example.iochat.`object`.UserCurrent
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL =
    APIConfig.APIChatApp
var gson = GsonBuilder()
    .setLenient()
    .create()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface ChatAppAPIService {
    @POST("/get-user-chated-by-id-mb")
    suspend fun getListUser(@Body yourId: UserCurrent): Array<User>

    @GET("/get-message-by-target")
    suspend fun getMessageByTarget(): Array<Message>

    @POST("/check-login-mb")
    suspend fun checkLogin(@Body data: Auth): UserCurrent
}

object ChatAPI {
    val retrofitService: ChatAppAPIService =
        retrofit.create(ChatAppAPIService::class.java)
}