package com.example.iochat.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iochat.network.ChatAPI
import com.example.iochat.`object`.PostGet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostViewModel : ViewModel() {
    private var _arrPost = MutableStateFlow<List<PostGet>>(emptyList())
    val arrPost
        get() = _arrPost.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                withContext(Dispatchers.IO) {
                    val arr = ChatAPI.retrofitService.getAllPost()
                    Log.d("SSS", "LENGTH POST: ${arr.size}")
                    _arrPost.value = arr
                }
            }
        }
    }

    fun handleClickPost(post: PostGet, onFinish: () -> Unit) {
        val jobPost = viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                TEST PROGRESS
                delay(3000)
                val isSuccess = ChatAPI.retrofitService.insertOnePost(post)
                Log.d("SSS", "isSuccess: $isSuccess")
                _arrPost.value = listOf(post)  + _arrPost.value
            }
        }
        jobPost.invokeOnCompletion {
            if(it != null){
                onFinish()
                Log.d("SSS", "ERROR post: "+it.message)
            }else {
                onFinish()
                Log.d("SSS", "POST SUCCESS!")
            }
        }
    }
}