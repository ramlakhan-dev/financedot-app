package com.rl.financedot.presentation.screen.resetpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rl.financedot.domain.model.OperationResult
import com.rl.financedot.domain.usecase.auth.AuthUseCase
import com.rl.financedot.presentation.state.OperationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

    private val _resetPasswordState = MutableStateFlow<OperationState>(OperationState.Idle)
    val resetPasswordState: StateFlow<OperationState> = _resetPasswordState.asStateFlow()

    fun sendResetPasswordLink(email: String) {
        viewModelScope.launch {
            _resetPasswordState.value = OperationState.Loading

            val result = authUseCase.sendResetPasswordLink(email)
            _resetPasswordState.value = when(result) {
                is OperationResult.Success -> OperationState.Success
                is OperationResult.Error -> OperationState.Error(result.message)
            }
        }
    }
}