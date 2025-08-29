package com.rl.financedot.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.rl.financedot.domain.repository.AuthRepository
import javax.inject.Inject

data class AuthUseCase(
    val signUp: SignUpUseCase,
    val signIn: SignInUseCase,
    val getCurrentUser: GetCurrentUserUseCase,
    val signOut: SignOutUseCase,
    val sendPasswordResetLink: SendPasswordResetLinkUseCase
)


class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser?> {
        return authRepository.signUp(email, password)
    }
}

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<FirebaseUser?> {
        return authRepository.signIn(email, password)
    }
}

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }
}

class SignOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() {
        authRepository.signOut()
    }
}

class SendPasswordResetLinkUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String): Result<Unit> {
        return authRepository.sendPasswordResetLink(email)
    }
}