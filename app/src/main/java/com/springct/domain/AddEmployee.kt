package com.springct.domain

import com.springct.domain.entity.EmployeeEntity

class AddEmployee(
    private val employeeRepository: EmployeeRepository
) {
    suspend operator fun invoke(employeeEntity: EmployeeEntity) =
        employeeRepository.addEmployee(employeeEntity)
}