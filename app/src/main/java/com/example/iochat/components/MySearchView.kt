import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iochat.ui.theme.InTextFieldSearch
import com.example.iochat.ui.theme.TextLeading
import com.example.iochat.ui.theme.ValueTextField

@Composable
fun MySeachView(
    modifier: Modifier = Modifier,
    value: String = "DEMO",
    onChangeText: (text: String) -> Unit = {},
    plaholder: String = "Tìm kiếm",
    backgroundInput: Color = InTextFieldSearch
) {
    TextField(
        value = value,
        onValueChange = {
            onChangeText(it)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = ValueTextField,
            disabledTextColor = Color.Transparent,
            backgroundColor = backgroundInput,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "SEARCH",
                tint = TextLeading
            )
        },
        placeholder = {
            Text(text = plaholder, color = TextLeading.copy(0.3f))
        },
        shape = RoundedCornerShape(12.dp)
    )
}

@Preview
@Composable
fun PrevMySearchView() {
    MySeachView()
}