package com.rl.financedot.di

import com.rl.financedot.data.repository.AuthRepositoryImpl
import com.rl.financedot.domain.repository.AuthRepository
import com.rl.financedot.domain.usecase.AuthUseCase
import com.rl.financedot.domain.usecase.GetCurrentUserUseCase
import com.rl.financedot.domain.usecase.SendPasswordResetLinkUseCase
import com.rl.financedot.domain.usecase.SignInUseCase
import com.rl.financedot.domain.usecase.SignOutUseCase
import com.rl.financedot.domain.usecase.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(
            signUp = SignUpUseCase(authRepository),
            signIn = SignInUseCase(authRepository),
            getCurrentUser = GetCurrentUserUseCase(authRepository),
            signOut = SignOutUseCase(authRepository),
            sendPasswordResetLink = SendPasswordResetLinkUseCase(authRepository)
        )
    }
}