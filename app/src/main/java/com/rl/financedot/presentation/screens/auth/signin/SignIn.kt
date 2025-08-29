package com.rl.financedot.presentation.screens.auth.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rl.financedot.R
import com.rl.financedot.presentation.navigation.Screen
import com.rl.financedot.presentation.screens.auth.AuthState

@Composable
fun SignIn(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    signInViewModel: SignInViewModel = hiltViewModel()
) {

    val state = signInViewModel.authState
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        signInViewModel.checkIfUserLoggedIn()
    }

    LaunchedEffect(state) {
        if (state is AuthState.Success) {
            navController.navigate(route = Screen.Home.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {



        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.email)
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.password)
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(id = R.string.forgot_password),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .clickable {
                        navController.navigate(route = Screen.ResetPassword.route)
                    }
            )
        }

        if (state is AuthState.Loading) {
            CircularProgressIndicator()
        }

        if (state is AuthState.Error) {
            Text(
                text = state.message,
                style = TextStyle (
                    color = MaterialTheme.colorScheme.error
                )
            )
        }

        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    signInViewModel.signIn(email, password)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sign_in)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = stringResource(id = R.string.sign_up_message),
                style = MaterialTheme.typography.bodyLarge,
            )

            Text(
                text = stringResource(id = R.string.sign_up),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clickable {
                        navController.navigate(route = Screen.SignUp.route)
                    }
            )
        }

    }
}