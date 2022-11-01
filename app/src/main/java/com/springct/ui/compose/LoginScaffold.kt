package com.springct.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.springct.R
import com.springct.ui.LoaderState
import com.springct.ui.view_model.LoginViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginScaffold(
    navigateToEmployeeList: () -> Unit,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = getViewModel()
) {
    val loginState = loginViewModel.loginState.value

    val isLoginEnabled = loginViewModel.isLoginEnabled.value

    loginViewModel.isLoginEnabled.value =
        (loginState.usernameState.value.value.isEmpty() || loginState.usernameState.isError.value || loginState.passwordState.value.value.isEmpty() || loginState.passwordState.isError.value).not()

    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White),
    ) { paddingValues ->

        when (loginViewModel.showLoader.value) {
            LoaderState.Loading -> {
                Dialog(onDismissRequest = {}) {
                    Box(modifier = Modifier
                        .size(100.dp)
                        .background(color = Color.White),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
            }
            LoaderState.ErrorDismissed -> {
                AlertDialog(
                    onDismissRequest = {},
                    buttons = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(onClick = {
                                loginViewModel.showLoader.value = LoaderState.Idle
                            }) {
                                Text(text = "Ok")
                            }
                        }
                    },
                    title = {
                        Text(text = "Alert")
                    },
                    text = {
                        Text(text = "Invalid Email or Password")
                    }
                )
            }
            LoaderState.Idle -> {
                //Do Nothing
            }
            LoaderState.SuccessDismissed -> {
                loginViewModel.showLoader.value = LoaderState.Idle
                navigateToEmployeeList()
            }
        }

        Column(
            modifier = Modifier
                .padding(paddingValues = PaddingValues(10.dp))
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(size = 300.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )

            TextInputField(
                value = loginState.usernameState.value.value,
                label = "Username",
                isError = loginState.usernameState.isError.value,
                errorMessage = loginState.usernameState.errorMessage.value,
            ) { newValue ->
                loginState.usernameState.value.value = newValue

                if (newValue.isEmpty()) {
                    loginState.usernameState.isError.value = true
                    loginState.usernameState.errorMessage.value = "Enter Username"
                } else {
                    loginState.usernameState.isError.value = false
                    loginState.usernameState.errorMessage.value = ""
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextInputField(
                value = loginState.passwordState.value.value,
                label = "Password",
                isError = loginState.passwordState.isError.value,
                errorMessage = loginState.passwordState.errorMessage.value,
                visualTransformation = PasswordVisualTransformation()
            ) { newValue ->
                loginState.passwordState.value.value = newValue

                if (newValue.isEmpty()) {
                    loginState.passwordState.isError.value = true
                    loginState.passwordState.errorMessage.value = "Enter Username"
                } else {
                    loginState.passwordState.isError.value = false
                    loginState.passwordState.errorMessage.value = ""
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                loginViewModel.login()
            }, enabled = isLoginEnabled) {
                Text(
                    text = "Log In"
                )
            }
        }
    }
}