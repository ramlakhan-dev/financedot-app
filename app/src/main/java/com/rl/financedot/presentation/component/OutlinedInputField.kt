package com.rl.financedot.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.rl.financedot.R

@Composable
fun OutlinedInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isError: Boolean = false,
    errorMessage: String? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions,
    passwordVisibility: Boolean = false,
    leadingIcon: ImageVector? = null,
    leadingIconDescription: String? = null
) {

    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { if (placeholder.isNotEmpty()) Text(text = placeholder) },
            isError = isError,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            visualTransformation = if (passwordVisibility && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = leadingIconDescription
                    )
                }
            },
            trailingIcon = {
                if (passwordVisibility) {
                    val icon = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    val desc = if (isPasswordVisible) stringResource(id = R.string.hide_password) else stringResource(id = R.string.show_password)
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = desc
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }

}