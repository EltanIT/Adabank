package com.example.adabank.feature_ababank.presentation.Login

data class LoginState(
    val number: String = "",
    val code: String = "",
    val preNumber: String = "+7",
    val nextState: Boolean = false,

    val isVerificationScreenOpen: Boolean = false,
    val isVerificationSuccessful: Boolean = false,
    val isLoginSuccessful: Boolean = false,

    val exception: String = ""
)
