package com.example.iochat.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.iochat.components.MyButton
import com.example.iochat.components.MyTextField
import com.example.iochat.model.LoginViewModel
import com.example.iochat.service.NotificationService
import com.example.iochat.ui.theme.BgGradientEnd
import com.example.iochat.ui.theme.BgGradientStart
import com.example.iochat.ui.theme.Green200
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    Scaffold(
        content = { paddingValues ->
            ContentLogin(
                paddingValues = paddingValues, navController = navController,
                loginViewModel = loginViewModel
            )
        }
    )
}

@Composable
fun ContentLogin(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    var username by remember {
        mutableStateOf(loginViewModel.getUsernameDefault())
    }
    var password by remember {
        mutableStateOf(
            loginViewModel.getPasswordDefault()
        )
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        BgGradientStart, BgGradientEnd
                    )
                )
            )
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Button(
                onClick = {
                    NotificationService(context).showNotification(
                        "Tin nhắn mới",
                        "OK"
                    )
                },
            ) {
                Text(text = "SHOW NOTI", color = Color.Black)
            }
            Text(
                text =
                SimpleDateFormat("MM/dd/yy/HH/mm/ss", Locale.getDefault()).format(
                    Calendar.getInstance().time
                ), color = Color.White
            )
            Text(
                text =
                    Calendar.getInstance().time.toString(), color = Color.White
            )

            Button(
                onClick = {
                    loginViewModel.startTimMing()
                },
            ) {
                Text(text = "Start timming", color = Color.Black)
            }
            Text(
                text = "ĐĂNG NHẬP",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(vertical = 12.dp)
            )
            Text(
                text = "Đăng nhập để tiếp tục", style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(bottom = 36.dp),
                color = Green200
            )
            MyTextField(
                value = username,
                onValueChange = {
                    username = it
                },
                label = "Địa chỉ email"
            )

            MyTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = "Mật khẩu"
            )
            MyButton(
                onClick = {
                    loginViewModel.checkLogin(username, password, navController)
                },
                label = "Đăng nhập"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PrevContentLogin() {
    ContentLogin()
}

@Preview(showBackground = true)
@Composable
fun PrevLoginScreen() {
    LoginScreen()
}

