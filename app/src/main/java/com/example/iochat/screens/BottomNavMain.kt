package com.example.iochat.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.iochat.navigate.BottomNavScreen
import com.example.iochat.navigate.listBottomNav
import com.example.iochat.ui.theme.BgBottomNav

@Composable
fun BottomNavMain(
    bottomNavController: NavHostController = rememberNavController(),
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                backgroundColor = BgBottomNav,
            ) {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                var selectedItemIndex by remember {
                    mutableStateOf(0)
                }
                listBottomNav.forEachIndexed { index, it ->
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any { destination ->
                            destination.route == it.route
                        } == true,
                        onClick = {
                            selectedItemIndex = index
                            bottomNavController.navigate(it.route)
                        },
                        alwaysShowLabel = selectedItemIndex == index,
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = null,
                                tint = if (selectedItemIndex == index) Color.White
                                else Color.White.copy(
                                    0.4f
                                )
                            )
                        },
                        label = {
                            Text(text = it.label, color = Color.White)
                        }
                    )
                }
            }
        }) { paddingValue ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavScreen.ChatScreen.route,
            Modifier.padding(paddingValue)
        ) {
            composable(BottomNavScreen.ChatScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(BottomNavScreen.PostScreen.route) {
                PostScreen()
            }
            composable(BottomNavScreen.ProfileScreen.route) {
                LoginScreen()
            }
        }
    }
}

@Preview
@Composable
fun PrevBottomNavMain() {
    BottomNavMain()
}