package com.rl.financedot.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Result<FirebaseUser?>
    suspend fun signIn(email: String, password: String): Result<FirebaseUser?>

    fun getCurrentUser(): FirebaseUser?
    fun signOut()

    suspend fun sendPasswordResetLink(email: String): Result<Unit>
}