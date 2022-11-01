package com.springct.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class AppScreen(val route: String) {
    object SplashScreen : AppScreen("/splash")
    object LoginScreen : AppScreen("/login")
    object EmployeeListScreen : AppScreen("/employees")
    object AddEmployeeScreen : AppScreen("/add-employee")
}

@Composable
fun AppNavigation(
    closeApp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.SplashScreen.route,
    ) {
        composable(
            route = AppScreen.SplashScreen.route
        ) {
            SplashScaffold(
                navigateToNextScreen = { isUserLoggedIn ->
                    if (isUserLoggedIn) {
                        navController.navigate(AppScreen.EmployeeListScreen.route)
                    } else {
                        navController.navigate(AppScreen.LoginScreen.route)
                    }
                },
                modifier = modifier
            )
        }
        composable(
            route = AppScreen.LoginScreen.route
        ) {
            LoginScaffold(
                navigateToEmployeeList = {
                    navController.navigate(AppScreen.EmployeeListScreen.route)
                }
            )
        }
        composable(
            route = AppScreen.EmployeeListScreen.route
        ) {
            ListEmployeesScaffold(
                closeApp = closeApp,
                navigateToAddEmployee = {
                    navController.navigate(AppScreen.AddEmployeeScreen.route)
                }
            )
        }
        composable(
            route = AppScreen.AddEmployeeScreen.route
        ) {
            AddEmployeeScaffold(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}