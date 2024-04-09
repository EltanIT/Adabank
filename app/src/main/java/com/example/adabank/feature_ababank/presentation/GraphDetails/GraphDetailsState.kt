package com.example.adabank.feature_ababank.presentation.GraphDetails

import com.example.adabank.feature_ababank.domain.model.TransactionData

data class GraphDetailsState(
    val transactionsList: List<TransactionData> = listOf(
        TransactionData(
            name = "Tokopedia",
            created_at = "26 Sep 2021",
            total = "220,98"
        ),
        TransactionData(
            name = "Apple Inc",
            created_at = "20 Sep 2021",
            total = "160,98"
        ),
        TransactionData(
            name = "ElectronicWe",
            created_at = "16 Sep 2021",
            total = "120,98"
        ),
        TransactionData(
            name = "Tokopedia",
            created_at = "1 Sep 2021",
            total = "20,98"
        ),
    ),
) {
}