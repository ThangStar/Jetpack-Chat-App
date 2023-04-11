package com.example.iochat.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.iochat.R
import com.example.iochat.ui.theme.TextLeading

@Composable
fun MyCard(
    modifierImage: Modifier = Modifier,
    modifierInnerContainer: Modifier = Modifier,
    image: String = "",
    title: String = "Hello WOrld",
    subTitle: String = "sub title",
    onClick: () -> Unit = {},
    isHasIcon: Boolean = false,
    isMultiColor: Boolean = false,
    listKeyWord: List<Char> = listOf('a', 'o', 'h'),
) {


    Box(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifierInnerContainer
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "Avatar",
                placeholder = painterResource(id = R.drawable.default_avatar),
                contentScale = ContentScale.Crop,
                modifier = modifierImage
                    .clip(CircleShape)
                    .size(46.dp)
                    .border(2.dp, Color.White, CircleShape)
            )
            Column(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                if (isMultiColor) {
                    Text(
//                        text = title,
//                        color = TextLeading,
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(bottom = 2.dp),
//                        fontWeight = FontWeight.Bold,
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = TextLeading,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                var isPrint = true
                                title.map { it ->
                                    isPrint = true
                                    listKeyWord.map { keyWord ->
                                        if (it.equals(keyWord, true)) {
                                            isPrint = false
                                            withStyle(style = SpanStyle(color = Color.Red)) {
                                                append(it)
                                            }
                                        }
                                    }
                                    if (isPrint) {
                                        append(it)
                                    }
                                }
                            }

                        }
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = TextLeading.copy(0.45f),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Light,
                                )
                            ) {
                                var isPrint = true
                                subTitle.map { it ->
                                    isPrint = true
                                    listKeyWord.map { keyWord ->
                                        if (it.equals(keyWord, true)) {
                                            isPrint = false
                                            withStyle(style = SpanStyle(color = Color.Red)) {
                                                append(it)
                                            }
                                        }
                                    }
                                    if (isPrint) {
                                        append(it)
                                    }
                                }
                            }
                        })
                } else {
                    Text(
                        text = title,
                        color = TextLeading,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 2.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = subTitle,
                        color = TextLeading.copy(0.45f),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light
                    )
                }

            }
            if (isHasIcon) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_chat_24),
                        contentDescription = "chat now",
                        tint = TextLeading.copy(0.6f)
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun PrevMyCard() {
    MyCard()
}