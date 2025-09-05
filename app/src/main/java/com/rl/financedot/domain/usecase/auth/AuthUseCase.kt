package com.rl.financedot.domain.usecase.auth

data class AuthUseCase (
    val signUp: SignUpUseCase,
    val signIn: SignInUseCase,
    val sendResetPasswordLink: SendResetPasswordLinkUseCase,
    val signOut: SignOutUseCase,
    val getCurrentUser: GetCurrentUserUseCase
)