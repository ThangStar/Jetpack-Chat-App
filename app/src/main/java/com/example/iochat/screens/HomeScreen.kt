package com.example.iochat.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.iochat.R
import com.example.iochat.model.HomeViewModel
import com.example.iochat.navigate.Screen

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            ToolbarChat()
        },
        content = {
            ListCardUser(paddingValues = it, homeViewModel = homeViewModel, navController = navController)
        }
    )
}

val list = arrayListOf<String>("as", "dsad")

@Composable
fun ListCardUser(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    LazyColumn() {
        items(homeViewModel.listUSer) {
            CardUser(name = it.fullname, navController = navController)
        }
    }
}

@Composable
fun CardUser(
    modifier: Modifier = Modifier.fillMaxWidth(),
    name: String = "NO NAME",
    navController: NavHostController = rememberNavController(),

    ) {
    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = modifier.clickable {
            navController.navigate(Screen.ChatScreen.route)
        }
    ) {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.default_avatar),
                contentDescription = null
            )
            Text(text = name, color = MaterialTheme.colors.surface)
        }
    }
}

@Preview
@Composable
fun PrevCardUser() {
    CardUser()
}

@Preview(showSystemUi = true)
@Composable
fun PrevHomeScreen() {
    HomeScreen()
}