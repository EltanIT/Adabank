package com.example.adabank.feature_ababank.presentation.PinCodeAuth

data class PinCodeAuthState(
    val code: String = "",
    val exception: String = "",
    val successful: Boolean = false,
    val fingerprintIsOpen: Boolean = false,
    val fingerprintIsSupport: Boolean = false
)