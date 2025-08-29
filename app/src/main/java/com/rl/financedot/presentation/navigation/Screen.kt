package com.rl.financedot.presentation.navigation

sealed class Screen(val route: String, val title: String) {
    object SignUp: Screen(route = "signup", title = "Sign Up")
    object SignIn: Screen(route = "signin", title = "Sign In")
    object ResetPassword: Screen(route = "reset-password", title = "Reset Password")
    object Home: Screen(route = "home", title = "Home")
}