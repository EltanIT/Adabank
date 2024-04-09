package com.example.adabank.feature_ababank.presentation.TransferDetail

sealed class TransferDetailEvent {
    object DeleteAmount: TransferDetailEvent()
    data class EnteredAmount(val value: String): TransferDetailEvent()
    data class EnteredSwipeCoordinate(val value: Float): TransferDetailEvent()
}