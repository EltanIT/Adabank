package com.example.adabank.feature_ababank.data.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {


    val PIN_CODE = "PIN_CODE"
    val SPLASH_SCREEN = "SPLASH_SCREEN"
    val PROFILE = "PROFILE"
    val BIOMETRIC_PROMPT = "BIOMETRIC_PROMPT"


    object PreferencesKeys{
        val PIN_CODE = stringPreferencesKey(Constants.PIN_CODE)
        val PROFILE = stringPreferencesKey(Constants.PROFILE)
        val SPLASH_SCREEN = booleanPreferencesKey(Constants.SPLASH_SCREEN)
        val BIOMETRIC_PROMPT = booleanPreferencesKey(Constants.BIOMETRIC_PROMPT)
    }
}