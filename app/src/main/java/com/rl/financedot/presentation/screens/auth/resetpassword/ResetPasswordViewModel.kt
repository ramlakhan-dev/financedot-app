package com.rl.financedot.presentation.screens.auth.resetpassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rl.financedot.domain.usecase.AuthUseCase
import com.rl.financedot.presentation.screens.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

    var resetPasswordState by mutableStateOf<AuthState>(AuthState.Idle)

    fun sendPasswordResetLink(email: String) {
        viewModelScope.launch {
            resetPasswordState = AuthState.Loading
            val result = authUseCase.sendPasswordResetLink(email)
            resetPasswordState = result.fold(
                onSuccess = { AuthState.ResetLinkSent},
                onFailure = { AuthState.Error(it.message ?: "Unknown Error")}
            )
        }
    }
}