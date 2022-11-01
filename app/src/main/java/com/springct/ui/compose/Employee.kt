package com.springct.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.springct.ui.state.EmployeeViewState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Employee(
    modifier: Modifier = Modifier,
    employeeViewState: EmployeeViewState
) {
    ListItem(
        modifier = modifier,
        text = {
            Text(text = "${employeeViewState.firstName} ${employeeViewState.lastName}")
        },
        secondaryText = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Age: ${employeeViewState.age}")

                Text(text = "${employeeViewState.address}, ${employeeViewState.city}")
            }
        }
    )
}