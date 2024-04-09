package com.example.adabank.feature_ababank.presentation.ProfileSettings

sealed class ProfileSettingsEvent {
    object TurnOffCardClick: ProfileSettingsEvent()
    object DarkThemeClick: ProfileSettingsEvent()
}