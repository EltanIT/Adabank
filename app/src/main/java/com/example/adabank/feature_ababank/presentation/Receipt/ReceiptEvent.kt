package com.example.adabank.feature_ababank.presentation.Receipt

sealed class ReceiptEvent {
    data class SelectCategory(val value: String): ReceiptEvent()
    object DownloadReceipt: ReceiptEvent()
}