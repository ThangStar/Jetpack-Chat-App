package com.example.iochat.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iochat.screens.BottomNavMain
import com.example.iochat.screens.ChatAppScreen
import com.example.iochat.screens.CoroutineDemo
import com.example.iochat.screens.HomeScreen
import com.example.iochat.screens.LoginScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = NavigateScreen.LoginNavigateScreen.route) {
        composable(
            route = NavigateScreen.BottomNavHomeScreen.route
        ) {
//            LoginScreen(navController = navController)
//            CoroutineDemo(navController = navController)
            BottomNavMain(
                bottomNavController = rememberNavController(),
                navController = navController
            )
        }
        composable(
            route = NavigateScreen.LoginNavigateScreen.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = NavigateScreen.HomeNavigateScreen.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = NavigateScreen.ChatNavigateScreen.route
        ) {
            ChatAppScreen(navController = navController)
        }
    }
}