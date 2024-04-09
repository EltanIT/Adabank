package com.example.adabank.feature_ababank.presentation.TopUpCard

data class TopUpCardState(
    val total: String = "0.00",
    val totalFloat: Float = 0f,
    val saldoActive: String = "450,49",
    val availableTotal: String = "50.000",
    val balance: String = "",
    val card: String = "5282 3456 7890 1289",
) {
}