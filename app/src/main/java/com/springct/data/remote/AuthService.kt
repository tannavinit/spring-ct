package com.springct.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    suspend fun doLogin(@Body loginParams: LoginParams): Response<LoginResponse>
}