package com.springct.ui.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.springct.domain.DoLogin
import com.springct.ui.LoaderState
import com.springct.ui.state.LoginState
import kotlinx.coroutines.launch

class LoginViewModel(
    private val doLogin: DoLogin
) : ViewModel() {

    private val _loginState: MutableState<LoginState> = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    val isLoginEnabled: MutableState<Boolean> = mutableStateOf(false)

    val showLoader: MutableState<LoaderState> = mutableStateOf(LoaderState.Idle)

    fun login() {
        showLoader.value = LoaderState.Loading

        viewModelScope.launch {
            val success = doLogin(
                _loginState.value.usernameState.value.value,
                _loginState.value.passwordState.value.value
            )

            if (success) {
                showLoader.value = LoaderState.SuccessDismissed
            } else {
                showLoader.value = LoaderState.ErrorDismissed
            }
        }
    }
}