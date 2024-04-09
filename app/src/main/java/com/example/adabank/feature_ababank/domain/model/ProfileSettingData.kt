package com.example.adabank.feature_ababank.domain.model

data class ProfileSettingData(
    val onlinePayment: Boolean = true,
    val atmWindrawals: Boolean = true,
    val paymentAbroad: Boolean = false,
) {
}