package com.example.iochat.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.iochat.model.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    Scaffold(
        content = { paddingValues ->
            ContentLogin(paddingValues = paddingValues, navController = navController,
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
    Box() {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(
                text = "ĐĂNG NHẬP",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = "Đăng nhập để tiếp tục", style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = {
                    username = it
                },
                label = {
                    Text(text = "Địa chỉ email")
                }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(text = "Mật khẩu")
                }
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { loginViewModel.checkLogin(username, password, navController) },
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 12.dp)

            ) {
                Text(text = "ĐĂNG NHẬP")
            }
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

