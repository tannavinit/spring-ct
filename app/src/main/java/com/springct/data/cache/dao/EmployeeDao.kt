package com.springct.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert
    fun insert(employeeCache: EmployeeCache) : Long

    @Query("SELECT * FROM EmployeeCache")
    fun getEmployees(): Flow<List<EmployeeCache>>
}