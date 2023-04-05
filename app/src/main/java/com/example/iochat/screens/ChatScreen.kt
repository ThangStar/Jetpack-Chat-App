@file:OptIn(ExperimentalMaterialApi::class)

package com.example.iochat.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.iochat.R
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.model.ChatViewModel
import com.example.iochat.service.NotificationService


@Composable
fun ChatAppScreen(
    chatViewModel: ChatViewModel = ChatViewModel(LocalContext.current),
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colors.primary),
        topBar = { ToolbarChat() },
        content = { paddingValues ->

            BackHandler(false) {
                chatViewModel.mSocket.disconnect()
            }
            ListCardMessage(
                paddingValues = paddingValues,
                chatViewModel = chatViewModel
            )
        },
        bottomBar = {
            ChatBottomBar(viewModel = chatViewModel)
        }
    )
}

@Composable
fun ChatBottomBar(
    viewModel: ChatViewModel = ChatViewModel(context = LocalContext.current),
) {
    var message by remember {
        mutableStateOf("")
    }
    Row(
        Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_call_24),
                    contentDescription = "image",
                    tint = Color(0xFF676767)
                )
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = message,
                onValueChange = { message = it },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.handleClick(message)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.round_send_24),
                            contentDescription = "Send"
                        )
                    }
                },
                placeholder = {
                    Text(text = "Nhập tin nhắn..")
                },
                colors = TextFieldDefaults.textFieldColors(MaterialTheme.colors.surface)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PrevChatBottomBar() {
    ChatBottomBar()
}

@Composable
fun ToolbarChat(paddingValues: PaddingValues = PaddingValues()) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)

        ) {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.surface
                )
            }
            Image(
                painter = painterResource(id = R.drawable.default_avatar),
                contentDescription = "AVATAR",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
            ) {
                Text(
                    text = UserCurrentConfig.idUserChating,
                    color = MaterialTheme.colors.surface,
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
                Text(
                    text = "Hoạt động 6 phút trước",
                    color = MaterialTheme.colors.surface,
                    fontSize = MaterialTheme.typography.caption.fontSize
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_videocam_24),
                    contentDescription = null,
                    tint = MaterialTheme.colors.surface
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_call_24),
                    contentDescription = null,
                    tint = MaterialTheme.colors.surface
                )
            }
        }
    }
}

@Composable
fun ListCardMessage(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    chatViewModel: ChatViewModel = ChatViewModel(LocalContext.current),
) {
    val array by chatViewModel._content.collectAsState()
    LazyColumn {
        items(array) {
            CardMessage(it.message)
        }
    }
}

@Composable
fun CardMessage(
    messageContent: String = "Hey guys. How r u today?",
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    var isShowTime = remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant,
        onClick = {
            isShowTime.value = !isShowTime.value
        }
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 16.dp
            )

        ) {
            Text(
                text = messageContent,
                color = MaterialTheme.colors.surface
            )
            AnimatedVisibility(visible = isShowTime.value) {
                Text(
                    text = "11h30p",
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(top = 6.dp),
                    fontSize = MaterialTheme.typography.caption.fontSize
                )
            }
        }
    }
}

@Preview
@Composable
fun PrevCardMessage() {
    CardMessage()
}

@Preview(
)
@Composable
fun PrevToolbarChat() {
    ToolbarChat()
}

@Preview(
    showBackground = true,
)
@Composable
fun PrevChatApp() {
    ChatAppScreen()
}

val name = arrayListOf("a", "b")