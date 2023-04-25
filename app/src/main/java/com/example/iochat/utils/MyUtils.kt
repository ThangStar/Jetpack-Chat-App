package com.example.iochat.utils

import androidx.compose.ui.graphics.Color

object MyUtils {
    private fun Color.toHexCode(hex: String): String {
        val red = this.red * 255
        val green = this.green * 255
        val blue = this.blue * 255
        return String.format(hex, red.toInt(), green.toInt(), blue.toInt())
    }
}