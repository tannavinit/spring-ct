package com.springct.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.springct.ui.view_model.ListEmployeesViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ListEmployeesScaffold(
    closeApp: () -> Unit,
    navigateToAddEmployee: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ListEmployeesViewModel = getViewModel()
) {

    val list = viewModel.employeesList.collectAsState()

    Scaffold(
        modifier = modifier
            .background(color = Color.White),
        topBar = {
            TopAppBar() {
                IconButton(onClick = closeApp) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }
                Text(
                    text = "Employees List",
                    style = TextStyle(
                        fontSize = TextUnit(18f, TextUnitType.Sp),
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navigateToAddEmployee) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
    ) { paddingValues ->

        if (list.value.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues = PaddingValues(8.dp))
            ) {

                items(list.value) { state ->
                    Employee(
                        employeeViewState = state
                    )

                    Spacer(
                        modifier = Modifier
                            .height(15.dp)
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(paddingValues = PaddingValues(15.dp))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "No employees added yet! Click '+' icon below to add",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                )
            }

        }
    }
}