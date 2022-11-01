package com.springct.domain

class DoLogin(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = authRepository.doLogin(email, password)
}