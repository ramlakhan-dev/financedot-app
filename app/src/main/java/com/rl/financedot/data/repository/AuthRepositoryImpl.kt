package com.rl.financedot.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.rl.financedot.domain.model.AuthResult
import com.rl.financedot.domain.model.OperationResult
import com.rl.financedot.domain.model.User
import com.rl.financedot.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): AuthRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): AuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user ?: return AuthResult.Error("User is null")

            val userData = User(user.uid, name, email)
            try {

                firebaseFirestore.collection("users").document(user.uid).set(userData).await()
                AuthResult.Authenticated
            } catch (e: Exception) {
                user.delete().await()
                AuthResult.Error(e.message ?: "Sign up failed")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign up failed")
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): AuthResult {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Authenticated
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Sign in failed")
        }
    }

    override suspend fun sendResetPasswordLink(email: String): OperationResult {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            OperationResult.Success
        } catch (e: Exception) {
            OperationResult.Error(e.message ?: "Failed to send reset password link")
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}