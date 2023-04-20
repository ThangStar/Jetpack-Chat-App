package com.example.iochat.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class CoroutineDemoViewModel : ViewModel() {
    private var _number1 by mutableStateOf(0)
    private var _number2 by mutableStateOf(0)

    private var job1: Job? = null


    val number1
        get() = _number1
    val number2
        get() = _number2
    val plusNumber: () -> Unit = {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            Log.d("ERROR", "JOB 1 cancel: ${throwable.message}")
        }
        viewModelScope.launch(exceptionHandler + CoroutineName("MAIN_SCOPE")) {
            withContext(Dispatchers.IO) {
                job1 = launch {
                    plusNumber1()
                }
                val job2 = launch {
                    repeat(5){
                        Log.d("SSS", "JOB 2 WORKING")
                    }
                }
                job1!!.invokeOnCompletion { error ->
                    if (error != null) {
                        Log.d("SSS", "FINISH WITH ERROR: " + error.message)
                    } else {
                        Log.d("SSS", "FINISH SUCCESS ")
                    }
                }
            }
        }
    }

    private suspend fun plusNumber1() {
        repeat(10) {
            delay(2000)
            ++_number1
            delay(1000)
            ++_number2
            Log.d("SSS", "NUMBER 1: $_number1")
            Log.d("SSS", Thread.currentThread().name)
        }
    }
    public fun stopViewModel(){
        job1!!.cancel()
    }
}