package com.example.adabank.di

import android.app.Application
import androidx.room.Room
import com.example.adabank.feature_ababank.data.ClientDataBase
import com.example.adabank.feature_ababank.data.manger.PinCodeMangerImpl
import com.example.adabank.feature_ababank.data.manger.ProfileManger
import com.example.adabank.feature_ababank.data.manger.ProfileMangerImpl
import com.example.adabank.feature_ababank.data.manger.SplashScreenMangerImpl
import com.example.adabank.feature_ababank.data.repository.ContactRepositoryImpl
import com.example.adabank.feature_ababank.data.repository.GetProfileRepositoryImpl
import com.example.adabank.feature_ababank.data.repository.SignInRepositoryImpl
import com.example.adabank.feature_ababank.data.repository.TransactionRepositoryImpl
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
object ClientModule {


    @Provides
    @Singleton
    fun provideTransactionDataStore(
        app: Application
    ): ClientDataBase{
        return Room.databaseBuilder(
            app,
            ClientDataBase::class.java,
            ClientDataBase.DATABASE_NAME
        ).build()
    }



    @Provides
    @Singleton
    fun getProfileMangerProvide(
        context: Application
    ): ProfileManger = ProfileMangerImpl(context)

    @Provides
    @Singleton
    fun pinCodeProvide(
        context: Application
    ): PinCodeManger = PinCodeMangerImpl(context)

    @Provides
    @Singleton
    fun splashScreenProvide(
        context: Application
    ): SplashScreenManger = SplashScreenMangerImpl(context)




    @Provides
    @Singleton
    fun signInProvide(): SignInRepository = SignInRepositoryImpl()

    @Provides
    @Singleton
    fun getProfileProvide(
        profileManger: ProfileManger
    ): GetProfileRepository = GetProfileRepositoryImpl(profileManger)

    @Provides
    @Singleton
    fun getContactsProvide(
        app: Application,
        database: ClientDataBase
    ): ContactsRepository = ContactRepositoryImpl(
        app,
        database.contactDao
    )

    @Provides
    @Singleton
    fun transactionsProvide(
        transactionDataBase: ClientDataBase
    ): TransactionRepository = TransactionRepositoryImpl(
        transactionDataBase.transactionDao
    )





    @Provides
    @Singleton
    fun profileUseCaseProvide(
        getProfileRepository: GetProfileRepository
    ): ProfileUseCase = ProfileUseCase(
        getProfileRepository,
        GetProfileName(getProfileRepository),
        GetProfileCard(getProfileRepository),
        GetProfileCastag(getProfileRepository),
        GetProfileEmail(getProfileRepository),
        GetProfileImage(getProfileRepository),
        GetProfileBalance(getProfileRepository),
    )

    @Provides
    @Singleton
    fun getContactsUseCaseProvide(
        contactsRepository: ContactsRepository
    ): ContactsUseCase = ContactsUseCase(
        GetAllContacts(contactsRepository),
        GetRecentContacts(contactsRepository),
        AddContact(contactsRepository),
    )

    @Provides
    @Singleton
    fun transactionUseCaseProvide(
        transactionRepository: TransactionRepository
    ): TransactionUseCase = TransactionUseCase(
        CreateTransaction(transactionRepository),
        GetTransactions(transactionRepository)
    )


    @Provides
    @Singleton
    fun pinCodeUseCaseProvide(
        pinCodeManger: PinCodeManger
    ) = PinCodeUseCase(
        SavePinCode(pinCodeManger),
        ReadPinCode(pinCodeManger)
    )

    @Provides
    @Singleton
    fun splashScreenUseCaseProvide(
        splashScreenManger: SplashScreenManger
    ) = SplashScreenUseCase(
        SaveSplashScreen(splashScreenManger),
        ReadSplashScreen(splashScreenManger)
    )
}