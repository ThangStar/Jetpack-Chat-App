package com.example.iochat.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iochat.ui.theme.BgMessageGet
import com.example.iochat.ui.theme.BgMessageSend
import com.example.iochat.ui.theme.TextLeading

@Composable
fun MyCardMessageGet(
    modifier: Modifier = Modifier,
    content: String = "Content",
    onClick: () -> Unit = {},
    isExpand: Boolean = false,
) {
    Card(
        backgroundColor = BgMessageGet.copy(0.3f),
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(vertical = 10.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 12.dp,
            bottomEnd = 12.dp
        )
    ) {
        Column() {
            Text(
                text = content,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
                color = TextLeading,
                fontSize = 18.sp
            )
            AnimatedVisibility(visible = isExpand) {
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyCardMessageSend(
    modifier: Modifier = Modifier,
    content: String = "Content",
    onClick: () -> Unit = {},
    isExpand: Boolean = false,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Spacer(modifier = Modifier.weight(1f))
        Card(
            backgroundColor = BgMessageSend,
            modifier = Modifier
                .clickable {
                    onClick()
                }
                .padding(vertical = 10.dp, horizontal = 12.dp),

            shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 12.dp,
                bottomStart = 12.dp
            )
        ) {
            Column() {
                Text(
                    text = content,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),
                    color = TextLeading,
                    fontSize = 18.sp
                )
                AnimatedVisibility(visible = isExpand) {
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
}

@Preview
@Composable
fun PrevMyCardMessageGet() {
    MyCardMessageGet()
}

@Preview
@Composable
fun PrevMyCardMessageSend() {
    MyCardMessageSend()
}