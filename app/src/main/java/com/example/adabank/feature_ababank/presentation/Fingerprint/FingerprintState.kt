package com.example.adabank.feature_ababank.presentation.Fingerprint

data class FingerprintState(
    val fingerprintPromptIsOpen: Boolean = true,
    val exception: String = "",
    val isSuccess: Boolean = false,

)