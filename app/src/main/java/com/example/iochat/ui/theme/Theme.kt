package com.example.iochat.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.Red,
    primaryVariant = Color.Red,

    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Gray100,
    primaryVariant = Color.White,
    secondary =  Color.Red,
    secondaryVariant = Silver200,

    background = Gray100,
    surface = Color.Black,
    onSecondary = Gray100,
)

@Composable
fun IOChatTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}