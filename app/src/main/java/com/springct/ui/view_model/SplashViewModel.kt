package com.springct.ui.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.springct.domain.GetLoginStatus

class SplashViewModel(
    private val getLoginStatus: GetLoginStatus
) : ViewModel() {

    val isLoggedIn: MutableState<Boolean?> = mutableStateOf(null)

    fun checkLoginStatus() = getLoginStatus().let {
        isLoggedIn.value = it
    }
}