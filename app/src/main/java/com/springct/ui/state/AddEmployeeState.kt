package com.springct.ui.state

data class AddEmployeeState(
    val firstName: TextInputFieldState = TextInputFieldState(),
    val lastName: TextInputFieldState = TextInputFieldState(),
    val age: TextInputFieldState = TextInputFieldState(),
    val address: TextInputFieldState = TextInputFieldState(),
    val city: TextInputFieldState = TextInputFieldState()
)
