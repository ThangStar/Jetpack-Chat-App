package com.example.iochat.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.model.HomeViewModel
import com.example.iochat.navigate.Screen

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            Text(text = "Xin chào ${UserCurrentConfig.fullname}", color = Color.Black)
        },
        content = {
            ListCardUser(
                paddingValues = it,
                homeViewModel = homeViewModel,
                navController = navController
            )
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
    val listUser = homeViewModel.listUSer.collectAsState()
    LazyColumn() {
        items(listUser.value) {
            CardUser(name = it.fullName, navController = navController, avatar = it.avatar, idUserChat = it._id)
        }
    }
}

@Composable
fun CardUser(
    modifier: Modifier = Modifier.fillMaxWidth(),
    name: String = "NO NAME",
    navController: NavHostController = rememberNavController(),
    avatar: String = "https://img.freepik.com/free-icon/motorcyclist_318-210119.jpg",
    idUserChat: String = "",
) {
    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = modifier.clickable {
            navController.navigate(Screen.ChatScreen.route)
            UserCurrentConfig.idUserChating = idUserChat
        },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = avatar,
                contentDescription = "avatar",
                modifier = Modifier.size(36.dp)
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