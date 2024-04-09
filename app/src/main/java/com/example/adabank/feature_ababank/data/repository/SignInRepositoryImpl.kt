package com.example.adabank.feature_ababank.data.repository

import com.example.adabank.feature_ababank.data.network.SupabaseInit.client
import com.example.adabank.feature_ababank.domain.repository.SignInRepository
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class SignInRepositoryImpl: SignInRepository {
    override suspend fun invoke() {
        client.auth.signInWith(Email){
            email = "user@gmail.com"
            password = "123456"
        }
    }
}