package com.springct.data

import com.springct.data.cache.dao.EmployeeCache
import com.springct.data.cache.dao.EmployeeDao
import com.springct.domain.EmployeeRepository
import com.springct.domain.entity.EmployeeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class EmployeeRepositoryImpl(
    private val employeeDao: EmployeeDao
) : EmployeeRepository {

    override suspend fun addEmployee(employeeEntity: EmployeeEntity): Boolean = employeeEntity.let {
        employeeDao.insert(
            employeeCache = EmployeeCache(
                firstName = it.firstName,
                lastName = it.lastName,
                age = it.age,
                address = it.address,
                city = it.city
            )
        ) > -1
    }

    override suspend fun getEmployees(): Flow<List<EmployeeEntity>> = flow {
        emitAll(
            employeeDao.getEmployees().map { list ->
                list.map {
                    EmployeeEntity(
                        id = it.id,
                        firstName = it.firstName,
                        lastName = it.lastName,
                        age = it.age,
                        address = it.address,
                        city = it.city
                    )
                }
            }
        )
    }
}