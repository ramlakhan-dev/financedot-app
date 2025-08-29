package com.rl.financedot.presentation.screens.auth.resetpassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.rl.financedot.R
import com.rl.financedot.presentation.screens.auth.AuthState

@Composable
fun ResetPassword(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel()
) {
    val state = resetPasswordViewModel.resetPasswordState
    var email by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        if (state is AuthState.ResetLinkSent) {
            navController.popBackStack()
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

        if (state is AuthState.Loading) {
            CircularProgressIndicator()
        }
        Button(
            onClick = {
                if (email.isNotEmpty()) {
                    resetPasswordViewModel.sendPasswordResetLink(email)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.send)
            )
        }
    }
}