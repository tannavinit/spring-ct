package com.springct.ui.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TextInputFieldState(
    val value: MutableState<String> = mutableStateOf(""),
    val errorMessage: MutableState<String> = mutableStateOf(""),
    val isError: MutableState<Boolean> = mutableStateOf(false),
)
