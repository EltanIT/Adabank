package com.example.adabank.feature_ababank.presentation.PinCodeAuth

sealed class PinCodeAuthEvent {
    data class EnteredCode(val value: String) : PinCodeAuthEvent()
    data class SetException(val value: String) : PinCodeAuthEvent()
    data object DeleteCode: PinCodeAuthEvent()
    data object Success: PinCodeAuthEvent()
    data object OpenFingerprint: PinCodeAuthEvent()
}