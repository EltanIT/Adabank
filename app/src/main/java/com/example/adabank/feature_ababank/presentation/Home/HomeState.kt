package com.example.adabank.feature_ababank.presentation.Home

import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.model.ProfileData
import com.example.adabank.feature_ababank.domain.model.TransactionData

data class HomeState(
    val name: String = "",
    val image: String = "",
    val balance: String = "",

    val sendHistory: List<ContactData> = emptyList(),
    val transactionHistory: List<TransactionData> = emptyList(),
    val exception: String = "",
    val isDataLoading: Boolean = true,

    var transferNav: Boolean = false
) {
}