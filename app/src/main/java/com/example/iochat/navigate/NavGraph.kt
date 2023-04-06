package com.example.iochat.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iochat.model.ChatViewModel
import com.example.iochat.screens.ChatAppScreen
import com.example.iochat.screens.HomeScreen
import com.example.iochat.screens.LoginScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(
            route = Screen.LoginScreen.route
        ){
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.HomeScreen.route
        ){
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.ChatScreen.route
        ){
            ChatAppScreen(navController = navController)
        }
    }
}