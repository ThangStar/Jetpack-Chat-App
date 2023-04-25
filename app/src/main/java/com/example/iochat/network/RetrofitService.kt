package com.example.iochat.network

import com.example.iochat.config.APIConfig
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.`object`.Auth
import com.example.iochat.`object`.Message
import com.example.iochat.`object`.Post
import com.example.iochat.`object`.PostGet
import com.example.iochat.`object`.User
import com.example.iochat.`object`.UserCurrent
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


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
    @POST("user/get-user-chated-by-id-mb")
    suspend fun getListUser(@Body yourId: UserCurrent): Array<User>

    @POST("/message/get-message-room-broadcast")
    suspend fun getMessageRoomBroadCast(@Body message: Message): Array<Message>

    @POST("/auth/check-login-mb")
    suspend fun checkLogin(@Body data: Auth): UserCurrent

    @GET("/user/search")
    suspend fun searchByNameOrEmail(@Query("q") keyWord: String): Array<User>

    @POST("/post/insert-post")
    suspend fun insertOnePost(@Body post:PostGet): Boolean

    @GET("/post/get-all-post")
    suspend fun getAllPost(): List<PostGet>
}

object ChatAPI {
    val retrofitService: ChatAppAPIService =
        retrofit.create(ChatAppAPIService::class.java)
}