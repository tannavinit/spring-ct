package com.springct.domain

import com.springct.domain.entity.EmployeeEntity
import kotlinx.coroutines.flow.Flow

interface EmployeeRepository {

    suspend fun addEmployee(employeeEntity: EmployeeEntity): Boolean

    suspend fun getEmployees(): Flow<List<EmployeeEntity>>
}