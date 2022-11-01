package com.springct.domain.entity

data class EmployeeEntity(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val address: String,
    val city: String
)
