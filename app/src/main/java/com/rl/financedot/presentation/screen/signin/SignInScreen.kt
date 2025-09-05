package com.rl.financedot.presentation.screen.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rl.financedot.R
import com.rl.financedot.presentation.component.OutlinedInputField

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onSignIn: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false)}

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.welcome_back),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            OutlinedInputField(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                value = email,
                onValueChange = {
                    email = it
                    emailError = email.isEmpty()
                },
                placeholder = stringResource(id = R.string.email),
                isError = emailError,
                errorMessage = if (emailError) "Email can't be empty" else null,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = Icons.Default.Mail,
                leadingIconDescription = stringResource(id = R.string.email)
            )

            OutlinedInputField(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                value = password,
                onValueChange = {
                    password = it
                    passwordError = password.isEmpty()
                },
                placeholder = stringResource(id = R.string.password),
                isError = passwordError,
                errorMessage = if (passwordError) "Password can't be empty" else null,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                passwordVisibility = true,
                leadingIcon = Icons.Default.Lock,
                leadingIconDescription = stringResource(id = R.string.password)
            )

            Text(
                text = stringResource(id = R.string.forgot_password),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .align(Alignment.End)
                    .clickable {
                        onForgotPasswordClick()
                    }
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.sign_in),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row {
            Text(
                text = stringResource(id = R.string.message_no_account),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.sign_up),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp)
                    .clickable {
                        onSignUpClick()
                    }
            )
        }
    }
}