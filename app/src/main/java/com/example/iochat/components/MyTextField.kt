package com.example.iochat.components

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iochat.ui.theme.*

@Composable
fun MyTextField(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 12.dp),
    value: String = "",
    label: String = "label",
    onValueChange: (text: String) -> Unit = {},
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(text = label, color = Green800)
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = ValueTextField,
            disabledTextColor = Color.Transparent,
            backgroundColor = InTextFieldSearch,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyTextFieldBasic(
    modifier: Modifier = Modifier,
    plaholder: String = "plaholder",
    onValueChange: (text: String) -> Unit = {},
    value: String = "value",
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            plaholder
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = TextLeading,
            disabledTextColor = Color.Transparent,
            backgroundColor = InTextFieldSearch,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun PrevMyTextFieldBasic() {
    MyTextFieldBasic()
}

@Preview
@Composable
fun PrevMyTextField() {
    MyTextField()
}