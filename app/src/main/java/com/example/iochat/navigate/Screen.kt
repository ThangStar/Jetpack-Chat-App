package com.example.iochat.navigate

sealed class Screen(val route: String) {
    object ChatScreen : Screen(route = "chat_screen")
    object HomeScreen : Screen(route = "home_screen")
}