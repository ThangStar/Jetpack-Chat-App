package com.example.iochat.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

//exam
val yourString = "HELLO WORLDoo OO"

@Composable
fun MultipleStylesInText() {

    Text(
        buildAnnotatedString {
            yourString.map {
                if(it.equals('o', true)){
                    withStyle(style = SpanStyle(color = Color.Blue)) {
                        append(it)
                    }
                }else {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                        append(it)
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun MPrevMultipleStylesInText() {
    MultipleStylesInText()
}