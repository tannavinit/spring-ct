package com.springct.data.remote

data class LoginResponse(
    val token: String? = null,
    val error: String? = null
)