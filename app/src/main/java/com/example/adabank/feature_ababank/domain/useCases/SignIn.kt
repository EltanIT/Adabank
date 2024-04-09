package com.example.adabank.feature_ababank.domain.useCases

import com.example.adabank.feature_ababank.domain.repository.SignInRepository

class SignIn(
    private val signInRepository: SignInRepository
) {


    suspend operator fun invoke(){
        signInRepository()
    }
}