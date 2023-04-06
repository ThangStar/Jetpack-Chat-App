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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.model.HomeViewModel
import com.example.iochat.navigate.Screen
import com.example.iochat.`object`.User

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
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


@Composable
fun ListCardUser(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val listUser = homeViewModel.listUSer.collectAsState()
    LazyColumn() {
        items(listUser.value) {
            CardUser(navController = navController, user = it)
        }
    }
}

@Composable
fun CardUser(
    modifier: Modifier = Modifier.fillMaxWidth(),
    navController: NavHostController = rememberNavController(),
    user: User = User("", "", "")
) {
    Card(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = modifier.clickable {
            navController.navigate(Screen.ChatScreen.route)
            UserCurrentConfig.idUserChating = user._id
            UserCurrentConfig.avatarUserChating = user.avatar
            UserCurrentConfig.nameUserChating = user.fullName
        },
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = user.avatar,
                contentDescription = "avatar",
                modifier = Modifier.size(36.dp)
            )
            Text(text = user.fullName, color = MaterialTheme.colors.surface)
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