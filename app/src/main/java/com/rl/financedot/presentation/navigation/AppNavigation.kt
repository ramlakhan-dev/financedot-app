package com.rl.financedot.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rl.financedot.presentation.screens.auth.resetpassword.ResetPassword
import com.rl.financedot.presentation.screens.auth.signin.SignIn
import com.rl.financedot.presentation.screens.auth.signup.SignUp
import com.rl.financedot.presentation.screens.home.Home

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.SignIn.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(route = Screen.SignUp.route) {
            SignUp(
                modifier = Modifier,
                navController = navController
            )
        }

        composable(route = Screen.SignIn.route) {
            SignIn(
                modifier = modifier,
                navController = navController
            )
        }

        composable(route = Screen.ResetPassword.route) {
            ResetPassword(
                modifier = modifier,
                navController = navController
            )
        }

        composable(route = Screen.Home.route) {
            Home(
                modifier = modifier,
                navController = navController
            )
        }
    }
}