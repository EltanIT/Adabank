package com.example.adabank.feature_ababank.presentation.Transfer

sealed class TransferEvent{
    data class EnteredSearch(val value: String): TransferEvent()
    data class SelectContact(val id: String): TransferEvent()
}