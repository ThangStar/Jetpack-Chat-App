package com.example.iochat.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.iochat.model.CoroutineDemoViewModel
import com.example.iochat.navigate.NavigateScreen

@Composable
fun CoroutineDemo(
    navController: NavController = rememberNavController(),
    coroutineDemoViewModel: CoroutineDemoViewModel = viewModel(),
) {
    Column() {

        Text(text = "NUMBER1: " + coroutineDemoViewModel.number1, color = Color.Black)
        Text(text = "NUMBER2: " + coroutineDemoViewModel.number2, color = Color.Black)

        Button(onClick = {
            coroutineDemoViewModel.plusNumber()
        }) {
            Text(text = "START THREAD", color = Color.Black)
        }
        Button(onClick = {
            navController.navigate(NavigateScreen.LoginNavigateScreen.route)
        }) {
            Text(text = "NEXT SCREEN", color = Color.Black)
        }
        Button(onClick = {
            coroutineDemoViewModel.stopViewModel()
        }) {
            Text(text = "STOP VIEW MODEL", color = Color.Black)
        }
    }
}