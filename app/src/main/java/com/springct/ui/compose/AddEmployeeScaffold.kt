package com.springct.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.springct.ui.LoaderState
import com.springct.ui.view_model.AddEmployeeViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalUnitApi::class)
@Composable
fun AddEmployeeScaffold(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddEmployeeViewModel = getViewModel()
) {

    val state = viewModel.addEmployeeState.value

    viewModel.isSubmitEnabled.value =
        (state.firstName.isError.value || state.lastName.isError.value || state.age.isError.value || state.address.isError.value || state.city.isError.value).not()

    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White),
        topBar = {
            TopAppBar() {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = navigateBack) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                    Text(
                        text = "Add Employee",
                        style = TextStyle(
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        },
    ) { paddingValues ->

        when (viewModel.showLoader.value) {
            LoaderState.Loading -> {
                Dialog(onDismissRequest = {}) {
                    Box(modifier = Modifier
                        .size(100.dp)
                        .background(color = Color.White),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
            }
            LoaderState.ErrorDismissed -> {
                AlertDialog(
                    onDismissRequest = {},
                    buttons = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(onClick = {
                                viewModel.showLoader.value = LoaderState.Idle
                            }) {
                                Text(text = "Ok")
                            }
                        }
                    },
                    title = {
                        Text(text = "Alert")
                    },
                    text = {
                        Text(text = "Some error occurred while adding an employee")
                    }
                )
            }
            LoaderState.Idle -> {
                //Do Nothing
            }
            LoaderState.SuccessDismissed -> {
                viewModel.showLoader.value = LoaderState.Idle
                navigateBack()
            }
        }

        Column(
            modifier = Modifier
                .padding(paddingValues = PaddingValues(10.dp))
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextInputField(
                value = state.firstName.value.value,
                label = "First Name",
                isError = state.firstName.isError.value,
                errorMessage = state.firstName.errorMessage.value,
            ) { newValue ->
                state.firstName.value.value = newValue

                if (newValue.isEmpty()) {
                    state.firstName.isError.value = true
                    state.firstName.errorMessage.value = "Enter First Name"
                } else {
                    state.firstName.isError.value = false
                    state.firstName.errorMessage.value = ""
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextInputField(
                value = state.lastName.value.value,
                label = "Last Name",
                isError = state.lastName.isError.value,
                errorMessage = state.lastName.errorMessage.value,
            ) { newValue ->
                state.lastName.value.value = newValue

                if (newValue.isEmpty()) {
                    state.lastName.isError.value = true
                    state.lastName.errorMessage.value = "Enter Last Name"
                } else {
                    state.lastName.isError.value = false
                    state.lastName.errorMessage.value = ""
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextInputField(
                value = state.age.value.value,
                label = "Age",
                isError = state.age.isError.value,
                errorMessage = state.age.errorMessage.value,
            ) { newValue ->
                state.age.value.value = newValue

                if (newValue.isEmpty()) {
                    state.age.isError.value = true
                    state.age.errorMessage.value = "Enter Age"
                } else {
                    state.age.isError.value = false
                    state.age.errorMessage.value = ""
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextInputField(
                value = state.address.value.value,
                label = "Address",
                isError = state.address.isError.value,
                errorMessage = state.address.errorMessage.value,
            ) { newValue ->
                state.address.value.value = newValue

                if (newValue.isEmpty()) {
                    state.address.isError.value = true
                    state.address.errorMessage.value = "Enter Address"
                } else {
                    state.address.isError.value = false
                    state.address.errorMessage.value = ""
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextInputField(
                value = state.city.value.value,
                label = "City",
                isError = state.city.isError.value,
                errorMessage = state.city.errorMessage.value,
            ) { newValue ->
                state.city.value.value = newValue

                if (newValue.isEmpty()) {
                    state.city.isError.value = true
                    state.city.errorMessage.value = "Enter City Name"
                } else {
                    state.city.isError.value = false
                    state.city.errorMessage.value = ""
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.addEmployee()
                },
                enabled = viewModel.isSubmitEnabled.value
            ) {
                Text(
                    text = "Submit"
                )
            }

        }
    }

}