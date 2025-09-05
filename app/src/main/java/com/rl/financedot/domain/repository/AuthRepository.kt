package com.rl.financedot.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.rl.financedot.domain.model.AuthResult
import com.rl.financedot.domain.model.OperationResult

interface AuthRepository {

    suspend fun signUp(name: String, email: String, password: String): AuthResult
    suspend fun signIn(email: String, password: String): AuthResult
    suspend fun sendResetPasswordLink(email: String): OperationResult
    fun signOut()
    fun getCurrentUser(): FirebaseUser?
}