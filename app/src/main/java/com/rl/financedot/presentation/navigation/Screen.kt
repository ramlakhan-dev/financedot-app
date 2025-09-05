package com.rl.financedot.presentation.navigation

sealed class Screen(val route: String) {
    object SignIn: Screen(route = "sign-in")
    object SignUp: Screen(route = "sign-up")
    object ResetPassword: Screen(route = "reset-password")
    object Home: Screen(route = "home")
}