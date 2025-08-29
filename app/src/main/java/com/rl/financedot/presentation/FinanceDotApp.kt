package com.rl.financedot.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rl.financedot.presentation.navigation.AppNavigation
import com.rl.financedot.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceDotApp() {

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute  = currentBackStack?.destination?.route

    val title = when (currentRoute) {
        Screen.SignUp.route -> Screen.SignUp.title
        Screen.SignIn.route -> Screen.SignIn.title
        Screen.Home.route -> Screen.Home.title
        else -> ""
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title
                    )
                }
            )
        }
    ) { innerPadding ->
        AppNavigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}