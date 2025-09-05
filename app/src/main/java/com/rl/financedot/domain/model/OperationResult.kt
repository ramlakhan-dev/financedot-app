package com.rl.financedot.domain.model

sealed class OperationResult {
    object Success: OperationResult()
    data class Error(val message: String): OperationResult()
}