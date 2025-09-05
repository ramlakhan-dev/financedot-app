package com.rl.financedot.domain.usecase.auth

import com.rl.financedot.domain.model.OperationResult
import com.rl.financedot.domain.repository.AuthRepository
import javax.inject.Inject

class SendResetPasswordLinkUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String): OperationResult = authRepository.sendResetPasswordLink(email)
}