package com.example.adabank.feature_ababank.presentation.Card

data class CardState(
    val isCardLimitEnable: Boolean = true,
    val cardLimit: String = "100",
    val balance: String = "",
    val card: String = "",


)