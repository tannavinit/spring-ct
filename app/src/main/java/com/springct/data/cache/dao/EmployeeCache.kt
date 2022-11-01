package com.springct.data.cache.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EmployeeCache(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val address: String,
    val city: String
)
