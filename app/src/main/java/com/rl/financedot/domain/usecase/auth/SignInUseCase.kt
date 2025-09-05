package com.rl.financedot.domain.usecase.auth

import com.rl.financedot.domain.model.AuthResult
import com.rl.financedot.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult = authRepository.signIn(email, password)
}