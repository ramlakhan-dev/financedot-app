package com.rl.financedot.domain.usecase.auth

import com.google.firebase.auth.FirebaseUser
import com.rl.financedot.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): FirebaseUser? = authRepository.getCurrentUser()
}