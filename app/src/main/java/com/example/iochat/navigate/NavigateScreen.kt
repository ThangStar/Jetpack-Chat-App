package com.example.iochat.navigate

sealed class NavigateScreen(val route: String) {
    object ChatNavigateScreen : NavigateScreen(route = "chat_screen")
    object BottomNavHomeScreen : NavigateScreen(route = "bottom_nav_home_screen")
    object HomeNavigateScreen : NavigateScreen(route = "home_screen")
    object LoginNavigateScreen : NavigateScreen(route = "login_screen")
}
