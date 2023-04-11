package com.example.iochat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.iochat.model.ChatViewModel
import com.example.iochat.navigate.SetUpNavGraph
import com.example.iochat.screens.ChatAppScreen
import com.example.iochat.ui.theme.IOChatTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.Socket
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IOChatTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        mSocket.disconnect()
    }
}

