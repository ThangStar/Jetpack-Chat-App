package com.example.iochat.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyButton (
    modifier: Modifier =  Modifier.fillMaxWidth(),
    onClick: () -> Unit = {},
    label: String = "BUTTON"
){
    Button(
        modifier = modifier,
        onClick = { onClick() },
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 20.dp),
    ) {
        Text(
            text = label,
            color = MaterialTheme.colors.background,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PrevMyButton() {
    MyButton()
}