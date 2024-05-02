package com.example.adabank.feature_ababank.domain.useCases.biometric

data class BiometricUseCase(
    val getBiometricState: GetBiometricState,
    val saveBiometricState: SaveBiometricState
) {
}