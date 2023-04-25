package com.example.iochat.components

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShowSnackBar(
    message: String = "",
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    isShow: Boolean = false,
) {
    Scaffold(scaffoldState = scaffoldState) { pad ->
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "MESSAGE",
                actionLabel = "Đóng",
            )
        }
    }

}