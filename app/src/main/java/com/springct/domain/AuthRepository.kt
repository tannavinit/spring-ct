package com.springct.domain

interface AuthRepository {
    suspend fun doLogin(email: String, password: String) : Boolean

    fun getLoginStatus(): Boolean
}