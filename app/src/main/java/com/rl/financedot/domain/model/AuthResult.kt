package com.rl.financedot.domain.model

sealed class AuthResult {
    object Authenticated: AuthResult()
    object Unauthenticated: AuthResult()
    data class Error(val message: String): AuthResult()
}