package com.rl.financedot.presentation.screens.auth.signup

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
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

    var authState by mutableStateOf<AuthState>(AuthState.Idle)

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authState = AuthState.Loading
            val result = authUseCase.signUp(email, password)
            authState = result.fold(
                onSuccess = { AuthState.Success(it)},
                onFailure = { AuthState.Error(it.message ?: "Unknown Error")}
            )
        }
    }
}