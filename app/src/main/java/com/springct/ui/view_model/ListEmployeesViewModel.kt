package com.springct.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.springct.domain.GetEmployees
import com.springct.ui.state.EmployeeViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class ListEmployeesViewModel(
    private val getEmployees: GetEmployees
) : ViewModel() {

    private val _employeesList = MutableStateFlow<List<EmployeeViewState>>(emptyList())
    val employeesList: StateFlow<List<EmployeeViewState>> = _employeesList

    init {
        getEmployeeList()
    }

    private fun getEmployeeList() {
        getEmployees()
            .map {
                _employeesList.value = it.map { entity ->
                    EmployeeViewState(
                        entity.firstName,
                        entity.lastName,
                        entity.age,
                        entity.address,
                        entity.city,
                    )
                }
            }.launchIn(viewModelScope)
    }
}