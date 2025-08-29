package com.rl.financedot.presentation.screens.auth.signin

import androidx.lifecycle.ViewModel
import com.rl.financedot.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
): ViewModel() {

}