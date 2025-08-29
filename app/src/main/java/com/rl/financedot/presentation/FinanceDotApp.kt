package com.rl.financedot.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rl.financedot.R
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

    val showBackArrow = currentRoute != Screen.SignIn.route && currentRoute != Screen.Home.route
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title
                    )
                },
                navigationIcon = {
                    if(showBackArrow) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.arrow_back)
                            )
                        }
                    } else {
                        null
                    }
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