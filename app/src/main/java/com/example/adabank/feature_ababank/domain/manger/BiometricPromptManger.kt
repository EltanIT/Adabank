package com.example.adabank.feature_ababank.domain.manger

interface BiometricPromptManger {

    suspend fun saveState(
        state: Boolean
    )

    suspend fun getState(): Boolean?
}