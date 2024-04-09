package com.example.adabank.feature_ababank.presentation.Graph

import com.example.adabank.R
import com.example.adabank.feature_ababank.domain.model.TransactionData

data class GraphState(
    val transactionsHistoryCategory: List<TransactionData> = listOf(
        TransactionData(
            name = "Equipment",
            description = "4 Transactions",
            image = R.drawable.ic_category_equipment,
            total = "220,98"
        ),
        TransactionData(
            name = "Entertaiment",
            description = "3 Transactions",
            image = R.drawable.ic_category_entertaiment,
            total = "160,98"
        ),
    ),
) {
}