package com.example.iochat.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.iochat.R
import com.example.iochat.config.UserCurrentConfig
import com.example.iochat.model.PostViewModel
import com.example.iochat.`object`.PostGet
import com.example.iochat.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun PostScreen(
    navController: NavHostController = rememberNavController(),
    postViewModel: PostViewModel = viewModel(),
) {
    Scaffold(modifier = Modifier.background(MaterialTheme.colors.primary),
        backgroundColor = BgGradientStart,
        content = { paddingValues ->
            var txtPost by remember {
                mutableStateOf("")
            }
            var isUploading by remember {
                mutableStateOf(false)
            }
            Column {
                PostHeader(paddingValues)
                ContainerYourPost(
                    value = txtPost, onValueChange = {
                        txtPost = it
                    },
                    postViewModel = postViewModel,
                    resetText = {
                        txtPost = ""
                    },
                    startLoading = { isUploading = true },
                    onFinish = {
                        isUploading = false
                    }
                )
                AnimatedVisibility(visible = isUploading) {
                    Text(
                        text = "Đang tải lên..",
                        color = TextLeading,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                    )
                }
                ListItemPost(postViewModel = postViewModel)
            }
        },
        bottomBar = {})
}

@Composable
fun ListItemPost(
    postViewModel: PostViewModel = viewModel(),
) {
    val arrPost = postViewModel.arrPost.collectAsState().value
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        itemsIndexed(arrPost) { _, it ->
            ItemPost(
                content = it.content,
                time = SimpleDateFormat("dd-MM-yyyy HH:mm").format(it.time)
            )

        }
    }
}

@Composable
fun PostHeader(
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(
            start = 12.dp, end = 12.dp, top = 24.dp, bottom = 16.dp
        )
    ) {
        Text(
            text = "Bài đăng", fontSize = 24.sp, color = TextLeading, fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContainerYourPost(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (it: String) -> Unit = {},
    postViewModel: PostViewModel = viewModel(),
    resetText: () -> Unit = {},
    startLoading: () -> Unit = {},
    onFinish: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 7.dp)
            .clip(RoundedCornerShape(12.dp))
            .height(IntrinsicSize.Min)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {
                HeaderYourPost()
                TextField(
                    value = value,
                    onValueChange = { onValueChange(it) },
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
            }
            Button(modifier = Modifier
                .background(BgActionPost)
                .fillMaxWidth()
                .fillMaxHeight(),
                onClick = {
                    startLoading()
                    postViewModel.handleClickPost(
                        PostGet(
                            "", idUser = UserCurrentConfig.id, value,
                            Date(), ""
                        ), onFinish
                    )
                    resetText()
                }) {
                Text(
                    text = "Đăng", color = Color.White, style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Composable
fun ItemPost(
    modifier: Modifier = Modifier,
    name: String = "User",
    avatar: String = "Avatar",
    content: String = "Content",
    time: String = "Vừa xong",

    ) {
    Column() {
        HeaderItemPost(name = name, avatar = avatar, time = time)
        ContentItemPost(content = content)
    }
}

@Composable
fun ContentItemPost(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp)),
    content: String = "Content",
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.like))
    var isLike by remember {
        mutableStateOf(false)
    }
    var arrColor = listOf(
        ColorRan1,
        ColorRan2,
        ColorRan3,
        ColorRan4,
    )
    var colorCurrent by remember {
        mutableStateOf(arrColor[(arrColor.indices).random()])
    }

    Card(
        backgroundColor = colorCurrent,
        modifier = modifier
    ) {
        Column() {
            Text(
                text = content, color = TextLeading,
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 18.dp)
            )
            Row() {
                LottieAnimation(
                    composition = composition,
                    clipSpec = if (isLike) LottieClipSpec.Progress(0.0f, 0.5f)
                    else LottieClipSpec.Progress(0.0f),
                    modifier = Modifier
                        .clickable { isLike = !isLike }
                        .size(58.dp)
                )
            }
        }
    }
}

@Composable
fun HeaderItemPost(
    modifier: Modifier = Modifier.fillMaxWidth(),
    name: String = "User",
    avatar: String = "Avatar",
    time: String = "Vừa xong",
) {
    Row(
        modifier
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(32.dp)
        )
        Column() {
            Text(
                text = name,
                color = TextLeading,
                modifier = Modifier.padding(horizontal = 9.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = time,
                color = TextLeading.copy(0.6f),
                modifier = Modifier.padding(horizontal = 9.dp),
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                contentDescription = "other",
                tint = TextLeading
            )
        }
    }
}

@Preview
@Composable
fun PrevItemPost() {
    ItemPost()
}

@Preview
@Composable
fun PrevContainerYourPost() {
    ContainerYourPost()
}

@Composable
fun HeaderYourPost(
    modifier: Modifier = Modifier.fillMaxWidth(0.8f),
    yourName: String = UserCurrentConfig.fullname,
) {
    Row(
        modifier
            .background(Color.White)
            .padding(horizontal = 9.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_avatar),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(24.dp)
        )
        Text(
            text = yourName,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 9.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PrevHeaderYourPost() {
    HeaderYourPost()
}