package com.example.iochat.navigate

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val icon: ImageVector, val label: String, val route: String){
    object ChatScreen: BottomNavScreen(icon = Icons.Outlined.Home, "Chat", route = "ChatScreen")
    object PostScreen: BottomNavScreen(icon = Icons.Outlined.Clear, label = "Post", route = "PostScreen")
    object ProfileScreen: BottomNavScreen(icon = Icons.Outlined.Person, label = "Profile", route = "ProfileScreen")

}
val listBottomNav = listOf(
    BottomNavScreen.ChatScreen,
    BottomNavScreen.PostScreen,
    BottomNavScreen.ProfileScreen
)
