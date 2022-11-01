package com.springct.ui.state

data class LoginState(
    val usernameState: TextInputFieldState = TextInputFieldState(),
    val passwordState: TextInputFieldState = TextInputFieldState()
)
