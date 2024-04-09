package com.example.adabank.feature_ababank.presentation.Login

sealed class LoginEvent {
    data class EnteredNumber(val value: String): LoginEvent()
    data class EnteredCode(val value: String): LoginEvent()
    data class SelectPreNumber(val value: String): LoginEvent()

    object DeleteNumber: LoginEvent()
    object OpenVerification: LoginEvent()
    object CloseVerification: LoginEvent()
    object LogIn: LoginEvent()

}