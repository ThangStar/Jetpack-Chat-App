package com.example.iochat.screens

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.iochat.R
import com.example.iochat.components.MyCardMessageGet
import com.example.iochat.components.MyCardMessageSend
import com.example.iochat.config.APIConfig
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.model.ChatViewModel
import com.example.iochat.`object`.Message
import com.example.iochat.ui.theme.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@Composable
fun ChatAppScreen(
    chatViewModel: ChatViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
) {
    BackHandler(false) {
        chatViewModel.disConnect()
        UserCurrentConfig.resetUserChating()
    }
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colors.primary),
        topBar = { ToolbarChat(chatViewModel = chatViewModel, navController = navController) },
        backgroundColor = BgGradientStart,
        content = { paddingValues ->
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
    viewModel: ChatViewModel = viewModel(),
) {
    var message by remember {
        mutableStateOf("")
    }
    Row(
        Modifier
            .background(ContainerInputSendMessage)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp))
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 26.dp),
            value = message,
            onValueChange = { message = it },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.handleClick(message)
                    message = ""
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_send_24),
                        contentDescription = "Send",
                        tint = TintIcon
                    )
                }
            },
            placeholder = {
                Text(text = "Nhập tin nhắn..", color = TextLeading)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = TextLeading,
                disabledTextColor = Color.Transparent,
                backgroundColor = BgInputSendMessage,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Send
            ),
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PrevChatBottomBar() {
    ChatBottomBar()
}

@Composable
fun ToolbarChat(
    chatViewModel: ChatViewModel = viewModel(),
    paddingValues: PaddingValues = PaddingValues(),
    navController: NavHostController = rememberNavController(),
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)

        ) {

            IconButton(onClick = {
                navController.popBackStack()
                UserCurrentConfig.resetUserChating()
                chatViewModel.disConnect()

            }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = null,
                    tint = TextLeading
                )
            }
            AsyncImage(
                model = "${APIConfig.APIChatApp}/images/${UserCurrentConfig.avatarUserChating}",
                contentDescription = "AVATAR",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                placeholder = painterResource(id = R.drawable.ic_launcher_background)
            )
            Column(
                modifier = Modifier.padding(horizontal = 8.dp),
            ) {
                Text(
                    text = UserCurrentConfig.nameUserChating,
                    color = TextLeading,
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
                Text(
                    text = "Hoạt động 6 phút trước",
                    color = TextLeading,
                    fontSize = MaterialTheme.typography.caption.fontSize
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_videocam_24),
                    contentDescription = null,
                    tint = TextLeading
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_call_24),
                    contentDescription = null,
                    tint = TextLeading
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ListCardMessage(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    chatViewModel: ChatViewModel = viewModel(),
) {
    val array by chatViewModel._content.collectAsState()
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        coroutineScope.launch {
            if (array.isNotEmpty()) {
                state.animateScrollToItem(
                    array.size - 1
                )
            }
        }
        items(array) {
            CardMessage(message = it)
        }
    }

}

@Composable
fun CardMessage(
    message: Message = Message(),
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    var isShowTime by remember {
        mutableStateOf(false)
    }
    if (message.idUserSend == UserCurrentConfig.id) {
        MyCardMessageSend(
            content = message.message,
            onClick = {
                isShowTime = !isShowTime
            },
            isExpand = isShowTime,
            time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(
                message.time
            )
        )
    } else {
        MyCardMessageGet(
            content = message.message,
            onClick = {
                isShowTime = !isShowTime
            },
            isExpand = isShowTime,
            time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(
                message.time
            )
        )
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