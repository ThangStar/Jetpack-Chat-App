package com.example.iochat.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iochat.screens.ChatAppScreen
import com.example.iochat.screens.HomeScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(
            route = Screen.ChatScreen.route
        ){
            ChatAppScreen(navController = navController)
        }

        composable(
            route = Screen.HomeScreen.route
        ){
            HomeScreen(navController = navController)
        }
    }
}