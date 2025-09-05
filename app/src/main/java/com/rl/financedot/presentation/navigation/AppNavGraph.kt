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
                modifier = modifier,
                onSignIn = {
                    navController.navigate(route = Screen.Home.route) {
                        popUpTo(route = Screen.SignIn.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onForgotPasswordClick = {
                    navController.navigate(route = Screen.ResetPassword.route)
                },
                onSignUpClick = {
                    navController.navigate(route = Screen.SignUp.route)
                }
            )
        }

        composable(
            route = Screen.SignUp.route
        ) {
            SignUpScreen(
                modifier = modifier,
                onSignUp = {
                    navController.navigate(route = Screen.Home.route) {
                        popUpTo(route = Screen.SignIn.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onSignInClick = {
                    navController.navigate(route = Screen.SignIn.route) {
                        popUpTo(route = Screen.SignIn.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = Screen.ResetPassword.route
        ) {
            ResetPasswordScreen(
                modifier = modifier,
                onSuccess = {
                    navController.popBackStack()
                }
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