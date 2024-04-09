package com.example.adabank.feature_ababank.presentation.Profile

import com.example.adabank.feature_ababank.domain.model.ProfileData
import com.example.adabank.feature_ababank.domain.model.ProfileSettingData

data class ProfileState(
    val profile: ProfileData = ProfileData(),
    val setting: ProfileSettingData = ProfileSettingData(),
    val exception: String = "",
    val primaryDevice: String = "",

    val onlinePayment: Boolean = true,
    val atmWindrawals: Boolean = true,
    val paymentAbroad: Boolean = false,

    val isProfileDetailsOpen: Boolean = false,
) {
}