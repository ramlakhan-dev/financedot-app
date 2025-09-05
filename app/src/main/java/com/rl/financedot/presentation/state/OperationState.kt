package com.rl.financedot.presentation.state

sealed class OperationState {
    object Idle: OperationState()
    object Loading: OperationState()
    object Success: OperationState()
    data class Error(val message: String): OperationState()
}