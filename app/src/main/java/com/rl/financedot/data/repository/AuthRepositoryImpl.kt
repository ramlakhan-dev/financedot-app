package com.rl.financedot.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rl.financedot.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepositoryImpl: AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun signUp(email: String, password: String): Result<FirebaseUser?> = try {
        val user = withContext(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password).await()
            auth.currentUser
        }
        Result.success(user)
    } catch (e: Exception) {
        Result.failure(e)

    }

    override suspend fun signIn(email: String, password: String): Result<FirebaseUser?> = try {
        val user = withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).await()
            auth.currentUser
        }
        Result.success(user)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override fun signOut() {
        auth.signOut()
    }

    override suspend fun sendPasswordResetLink(email: String): Result<Unit> = try {
        withContext(Dispatchers.IO) {
            auth.sendPasswordResetEmail(email).await()
        }
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}