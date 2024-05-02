package com.example.adabank.feature_ababank.domain.useCases.biometric

import com.example.adabank.feature_ababank.domain.manger.BiometricPromptManger
import javax.inject.Inject

class SaveBiometricState(
    private val manger: BiometricPromptManger
) {


    suspend operator fun invoke(state: Boolean){
        return manger.saveState(state)
    }
}