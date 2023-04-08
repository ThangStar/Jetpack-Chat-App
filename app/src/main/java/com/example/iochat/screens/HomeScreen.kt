package com.example.iochat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.iochat.components.MyCard
import com.example.iochat.components.MyTextField
import com.example.iochat.components.MyTextFieldBasic
import com.example.iochat.config.APIConfig
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.model.HomeViewModel
import com.example.iochat.navigate.Screen
import com.example.iochat.`object`.User
import com.example.iochat.ui.theme.BgGradientEnd
import com.example.iochat.ui.theme.BgGradientStart
import com.example.iochat.ui.theme.TextLeading

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    var txtSearch by remember {
        mutableStateOf("")
    }
    Scaffold(
        backgroundColor = BgGradientStart,
        topBar = {
//            Text(text = "Xin chào ${UserCurrentConfig.fullname}", color = Color.Black)
            Column {
                Text(
                    text = "Tin nhắn",
                    modifier = Modifier.padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 24.dp,
                        bottom = 16.dp
                    ),
                    fontSize = 24.sp,
                    color = TextLeading,
                    fontWeight = FontWeight.Bold
                )
                MyTextFieldBasic(
                    value = txtSearch,
                    plaholder = "Tìm kiếm",
                    onValueChange = {
                        txtSearch = it
                    },
                    modifier = Modifier
                        .padding(bottom = 28.dp, start = 12.dp, end = 12.dp)
                        .fillMaxWidth()
                )

            }
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
    user: User = User("", "", ""),
) {

    MyCard(
        modifier = Modifier,
        image = "${APIConfig.APIChatApp}/images/${user.avatar}",
        title = user.fullName,
        subTitle = "This is new chat",
        onClick = {
            navController.navigate(Screen.ChatScreen.route)
            UserCurrentConfig.idUserChating = user._id
            UserCurrentConfig.avatarUserChating = user.avatar
            UserCurrentConfig.nameUserChating = user.fullName
        }
    )
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