package com.springct.ui.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.springct.domain.AddEmployee
import com.springct.domain.entity.EmployeeEntity
import com.springct.ui.LoaderState
import com.springct.ui.state.AddEmployeeState
import kotlinx.coroutines.launch

class AddEmployeeViewModel(
    private val addEmployee: AddEmployee
) : ViewModel() {

    private val _addEmployeeState: MutableState<AddEmployeeState> =
        mutableStateOf(AddEmployeeState())
    val addEmployeeState: State<AddEmployeeState> = _addEmployeeState

    val isSubmitEnabled: MutableState<Boolean> = mutableStateOf(false)

    val showLoader: MutableState<LoaderState> = mutableStateOf(LoaderState.Idle)

    fun addEmployee() {
        showLoader.value = LoaderState.Loading
        viewModelScope.launch {
            val success = addEmployee(
                EmployeeEntity(
                    firstName = _addEmployeeState.value.firstName.value.value,
                    lastName = _addEmployeeState.value.lastName.value.value,
                    age = _addEmployeeState.value.age.value.value.toInt(),
                    address = _addEmployeeState.value.address.value.value,
                    city = _addEmployeeState.value.city.value.value,
                )
            )

            showLoader.value =
                if (success) LoaderState.SuccessDismissed else LoaderState.ErrorDismissed
        }
    }
}