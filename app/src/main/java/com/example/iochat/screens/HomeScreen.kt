package com.example.iochat.screens

import MySeachView
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.iochat.R
import com.example.iochat.components.MyCard
import com.example.iochat.config.APIConfig
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.model.HomeViewModel
import com.example.iochat.navigate.NavigateScreen
import com.example.iochat.`object`.User
import com.example.iochat.ui.theme.BgGradientStart
import com.example.iochat.ui.theme.BgSearchInput
import com.example.iochat.ui.theme.TextLeading

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    var txtSearch by remember {
        mutableStateOf("")
    }
    Scaffold(backgroundColor = BgGradientStart, topBar = {
        Column {
            var isExpand by remember {
                mutableStateOf(false)
            }
            var txtSearchFriends by remember {
                mutableStateOf("")
            }
            HomeHeader(onExpand = {
                isExpand = !isExpand
            })
            AddFriendsContainer(
                isExpand = isExpand,
                txtSearch = txtSearchFriends,
                onChangeSearch = {
                    txtSearchFriends = it
                },
                homeViewModel = homeViewModel,
                navController = navController
            )
            MySeachView(
                value = txtSearch,
                onChangeText = {
                    txtSearch = it
                },
                modifier = Modifier
                    .padding(start = 12.dp, end = 12.dp, bottom = 40.dp)
                    .fillMaxWidth()
            )
        }
    }, content = {
        ListCardUser(
            paddingValues = it, homeViewModel = homeViewModel, navController = navController
        )
    })
}

@Composable
fun AddFriendsContainer(
    txtSearch: String = "",
    onChangeSearch: (it: String) -> Unit = {},
    isExpand: Boolean = true,
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    AnimatedVisibility(
        visible = isExpand,
        modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 9.dp, top = 16.dp),
        enter = expandVertically(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),

            )
    ) {
        Column(
            modifier = Modifier.border(
                1.dp, TextLeading.copy(0.2f), RoundedCornerShape(12.dp)
            )
        ) {
            MySeachView(
                value = txtSearch,
                onChangeText = {
                    onChangeSearch(it)
                    homeViewModel.searchByKeyWord(it)
                },
                plaholder = "Nhập email hoặc tên người đó..",
                modifier = Modifier.fillMaxWidth(),
                backgroundInput = BgSearchInput.copy(0.5f)
            )
            val arrSearch by homeViewModel.arrResultSearchFriend.collectAsState()
            if (arrSearch.isEmpty()) {
                Text(
                    text = "Tìm kiếm và trò truyện với bạn bè ngay thôi!",
                    color = TextLeading,
                    modifier = Modifier.padding(
                        top = 22.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 12.dp
                    ),
                    fontSize = 16.sp
                )
            }

            LazyColumn() {
                item {
                    if (arrSearch.isNotEmpty()) {
                        Text(
                            text = "Tìm thấy ${arrSearch.size} kết quả",
                            color = TextLeading,
                            modifier = Modifier.padding(top = 22.dp, start = 16.dp, end = 16.dp),
                            fontSize = 12.sp
                        )
                    }

                }
                items(arrSearch) {
                    MyCard(
                        modifierImage = Modifier.size(34.dp),
                        modifierInnerContainer = Modifier.padding(0.dp),
                        title = it.fullName,
                        subTitle = it.email,
                        image = "${APIConfig.APIChatApp}/images/${it.avatar}",
                        isHasIcon = true,
                        isMultiColor = true,
                        listKeyWord = txtSearch.toList(),
                        onClick = {
                            UserCurrentConfig.idUserChating = it._id
                            UserCurrentConfig.avatarUserChating = it.avatar
                            UserCurrentConfig.nameUserChating = it.fullName

                            navController.navigate(NavigateScreen.ChatNavigateScreen.route)
                        }
                    )
                }
            }

        }
    }


}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PrevAddFiriendsContainer() {
    AddFriendsContainer()
}

@Composable
fun HomeHeader(
    onExpand: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            start = 12.dp, end = 12.dp, top = 24.dp
        )
    ) {
        Text(
            text = "Tin nhắn", fontSize = 24.sp, color = TextLeading, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                onExpand()
            },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_person_add_24),
                contentDescription = "add friends",
                tint = TextLeading,
            )
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PrevHomeHeader() {
    HomeHeader()
}

@Composable
fun ListCardUser(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    homeViewModel: HomeViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val listUser = homeViewModel.listUSer.collectAsState()
    LazyColumn(
        contentPadding = PaddingValues(vertical = 5.dp)
    ) {
        items(listUser.value) {
            CardUser(navController = navController, user = it)
        }
    }
}

@Composable
fun CardUser(
    modifier: Modifier = Modifier.fillMaxWidth(),
    navController: NavHostController = rememberNavController(),
    user: User = User("", "", "", ""),
) {

    MyCard(modifierImage = Modifier,
        image = "${APIConfig.APIChatApp}/images/${user.avatar}",
        title = user.fullName,
        subTitle = "This is new chat",
        onClick = {
            navController.navigate(NavigateScreen.ChatNavigateScreen.route)
            UserCurrentConfig.idUserChating = user._id
            UserCurrentConfig.avatarUserChating = user.avatar
            UserCurrentConfig.nameUserChating = user.fullName
        })
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