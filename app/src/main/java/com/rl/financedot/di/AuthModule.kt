package com.rl.financedot.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rl.financedot.data.repository.AuthRepositoryImpl
import com.rl.financedot.domain.repository.AuthRepository
import com.rl.financedot.domain.usecase.auth.AuthUseCase
import com.rl.financedot.domain.usecase.auth.GetCurrentUserUseCase
import com.rl.financedot.domain.usecase.auth.SendResetPasswordLinkUseCase
import com.rl.financedot.domain.usecase.auth.SignInUseCase
import com.rl.financedot.domain.usecase.auth.SignOutUseCase
import com.rl.financedot.domain.usecase.auth.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore): AuthRepository = AuthRepositoryImpl(firebaseAuth, firebaseFirestore)

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(
            signUp = SignUpUseCase(authRepository),
            signIn = SignInUseCase(authRepository),
            sendResetPasswordLink = SendResetPasswordLinkUseCase(authRepository),
            signOut = SignOutUseCase(authRepository),
            getCurrentUser = GetCurrentUserUseCase(authRepository)
        )
    }
}