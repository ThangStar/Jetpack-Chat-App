package com.example.iochat.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.iochat.R
import com.example.iochat.ui.theme.TextLeading

@Composable
fun MyCard(
    modifier: Modifier = Modifier,
    image: String = "",
    title: String = "title",
    subTitle: String = "sub title",
    onClick: () -> Unit = {},
) {


    Box(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = "Avatar",
                placeholder = painterResource(id = R.drawable.default_avatar),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(47.dp)
                    .border(2.dp, Color.White, CircleShape)
            )
            Column(
                modifier = modifier.padding(horizontal = 12.dp)
            ) {
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
    }
}

@Preview()
@Composable
fun PrevMyCard() {
    MyCard()
}