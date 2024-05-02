package com.example.adabank.feature_ababank.presentation.Fingerprint

sealed class FingerprintEvent {
    data object ShowBiometricPrompt: FingerprintEvent()
    data class SetException(val value: String): FingerprintEvent()
    data object Successful: FingerprintEvent()
}