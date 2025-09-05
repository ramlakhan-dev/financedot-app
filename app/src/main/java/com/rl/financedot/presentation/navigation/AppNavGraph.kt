package com.rl.financedot.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rl.financedot.presentation.screen.home.HomeScreen
import com.rl.financedot.presentation.screen.resetpassword.ResetPasswordScreen
import com.rl.financedot.presentation.screen.signin.SignInScreen
import com.rl.financedot.presentation.screen.signup.SignUpScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    isUserSignedIn: Boolean
) {

    NavHost(
        navController = navController,
        startDestination = if (isUserSignedIn) Screen.Home.route else Screen.SignIn.route
    ) {
        composable(
            route = Screen.SignIn.route
        ) {
            SignInScreen(
                modifier = modifier
            )
        }

        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(
                modifier = modifier
            )
        }

        composable(
            route = Screen.ResetPassword.route
        ) {
            ResetPasswordScreen(
                modifier = modifier
            )
        }

        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(
                modifier = modifier
            )
        }
    }
}