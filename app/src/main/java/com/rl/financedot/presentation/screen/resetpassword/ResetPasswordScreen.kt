package com.rl.financedot.presentation.screen.resetpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rl.financedot.R
import com.rl.financedot.presentation.component.OutlinedInputField
import com.rl.financedot.presentation.state.OperationState

@Composable
fun ResetPasswordScreen(
    modifier: Modifier = Modifier,
    onSuccess: () -> Unit
) {

    val resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel()
    val resetPasswordState by resetPasswordViewModel.resetPasswordState.collectAsState()

    var email by rememberSaveable { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }

    LaunchedEffect(resetPasswordState) {
        if(resetPasswordState is OperationState.Success) {
            onSuccess()
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
            .padding(16.dp)
            .imePadding()
    ) {
        Text(
            text = stringResource(id = R.string.reset_password),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = stringResource(id = R.string.reset_password_message),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 24.dp)
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
                imeAction = ImeAction.Done
            ),
            leadingIcon = Icons.Default.Mail,
            leadingIconDescription = stringResource(id = R.string.email)
        )

        if (resetPasswordState is OperationState.Error) {
            Text(
                text = (resetPasswordState as OperationState.Error).message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Button(
            onClick = {
                resetPasswordViewModel.sendResetPasswordLink(email)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .height(48.dp),
            shape = RoundedCornerShape(10.dp),
            enabled = email.isNotEmpty()
        ) {
            if(resetPasswordState is OperationState.Loading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text(
                    text = stringResource(id = R.string.send),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}