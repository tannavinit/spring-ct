package com.springct.data

import com.springct.data.cache.SharedPreferencesDB
import com.springct.data.remote.AuthService
import com.springct.data.remote.LoginParams
import com.springct.domain.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val sharedPreferences: SharedPreferencesDB
) : AuthRepository {

    override suspend fun doLogin(email: String, password: String): Boolean {
        val response = authService.doLogin(
            LoginParams(
                email = email,
                password = password
            )
        )

        var isSuccess = false

        if (response.isSuccessful) {
            if (response.code() == 200) {
                response.body()?.token?.let { token ->
                    sharedPreferences.setString(SharedPreferencesDB.Keys.TOKEN, token)
                    isSuccess = true
                } ?: run {
                    isSuccess = false
                }
            } else if (response.code() == 400) {
                isSuccess = false
            }
        }

        return isSuccess
    }

    override fun getLoginStatus(): Boolean =
        sharedPreferences.getString(SharedPreferencesDB.Keys.TOKEN) != null
}