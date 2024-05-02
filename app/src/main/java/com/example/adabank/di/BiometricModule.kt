package com.example.adabank.di

import android.app.Application
import androidx.room.Room
import com.example.adabank.feature_ababank.data.manger.BiometricPromptMangerImpl
import com.example.adabank.feature_ababank.data.storage.ClientDataBase
import com.example.adabank.feature_ababank.data.manger.PinCodeMangerImpl
import com.example.adabank.feature_ababank.data.manger.ProfileManger
import com.example.adabank.feature_ababank.data.manger.ProfileMangerImpl
import com.example.adabank.feature_ababank.data.manger.SplashScreenMangerImpl
import com.example.adabank.feature_ababank.data.repository.ContactRepositoryImpl
import com.example.adabank.feature_ababank.data.repository.GetProfileRepositoryImpl
import com.example.adabank.feature_ababank.data.repository.SignInRepositoryImpl
import com.example.adabank.feature_ababank.data.repository.TransactionRepositoryImpl
import com.example.adabank.feature_ababank.domain.manger.BiometricPromptManger
import com.example.adabank.feature_ababank.domain.manger.PinCodeManger
import com.example.adabank.feature_ababank.domain.manger.SplashScreenManger
import com.example.adabank.feature_ababank.domain.repository.ContactsRepository
import com.example.adabank.feature_ababank.domain.repository.GetProfileRepository
import com.example.adabank.feature_ababank.domain.repository.SignInRepository
import com.example.adabank.feature_ababank.domain.repository.TransactionRepository
import com.example.adabank.feature_ababank.domain.useCases.CreateTransaction
import com.example.adabank.feature_ababank.domain.useCases.GetTransactions
import com.example.adabank.feature_ababank.domain.useCases.PinCodeUseCase
import com.example.adabank.feature_ababank.domain.useCases.ProfileUseCase
import com.example.adabank.feature_ababank.domain.useCases.ReadPinCode
import com.example.adabank.feature_ababank.domain.useCases.ReadSplashScreen
import com.example.adabank.feature_ababank.domain.useCases.SavePinCode
import com.example.adabank.feature_ababank.domain.useCases.SaveSplashScreen
import com.example.adabank.feature_ababank.domain.useCases.SplashScreenUseCase
import com.example.adabank.feature_ababank.domain.useCases.TransactionUseCase
import com.example.adabank.feature_ababank.domain.useCases.biometric.BiometricUseCase
import com.example.adabank.feature_ababank.domain.useCases.biometric.GetBiometricState
import com.example.adabank.feature_ababank.domain.useCases.biometric.SaveBiometricState
import com.example.adabank.feature_ababank.domain.useCases.contacts.AddContact
import com.example.adabank.feature_ababank.domain.useCases.contacts.ContactsUseCase
import com.example.adabank.feature_ababank.domain.useCases.contacts.GetAllContacts
import com.example.adabank.feature_ababank.domain.useCases.contacts.GetRecentContacts
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileBalance
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileCard
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileCastag
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileEmail
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileImage
import com.example.adabank.feature_ababank.domain.useCases.profile.GetProfileName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BiometricModule {

    @Provides
    @Singleton
    fun getBiometricMangerProvide(
        context: Application
    ): BiometricPromptManger = BiometricPromptMangerImpl(context)


    @Provides
    @Singleton
    fun getBiometricUseCaseProvide(
        manger: BiometricPromptManger
    ): BiometricUseCase = BiometricUseCase(
        GetBiometricState(manger),
        SaveBiometricState(manger)
    )

}