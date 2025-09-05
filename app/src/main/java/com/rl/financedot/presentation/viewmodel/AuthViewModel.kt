package com.rl.financedot.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.rl.financedot.domain.usecase.auth.AuthUseCase
import com.rl.financedot.presentation.state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

    private val _userState = MutableStateFlow<AuthState>(AuthState.Idle)
    val userState: StateFlow<AuthState> = _userState.asStateFlow()

    init {
        val user = authUseCase.getCurrentUser()
        if (user != null) {
            _userState.value = AuthState.Authenticated
        } else {
            _userState.value = AuthState.Unauthenticated
        }
    }
}