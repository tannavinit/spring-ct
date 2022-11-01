package com.springct.domain

import com.springct.domain.entity.EmployeeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GetEmployees(
    private val employeeRepository: EmployeeRepository
) {
    operator fun invoke(): Flow<List<EmployeeEntity>> = flow {
        emitAll(
            employeeRepository.getEmployees()
        )
    }
}