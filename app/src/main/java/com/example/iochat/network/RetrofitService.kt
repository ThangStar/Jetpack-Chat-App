package com.example.iochat.network

import com.example.iochat.`object`.User
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "http://192.168.1.13:3000"
var gson = GsonBuilder()
    .setLenient()
    .create()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(gson))
    .baseUrl(BASE_URL)
    .build()

interface ChatAppAPIService {
    @GET("/test")
    suspend fun getListUser(): Array<User>
}

object ChatAPI {
    val retrofitService: ChatAppAPIService =
        retrofit.create(ChatAppAPIService::class.java)
}