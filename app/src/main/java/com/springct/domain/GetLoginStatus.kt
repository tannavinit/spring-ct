package com.springct.domain

class GetLoginStatus(
    private val authRepository: AuthRepository
) {
    operator fun invoke() : Boolean = authRepository.getLoginStatus()
}