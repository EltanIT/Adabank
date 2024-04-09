package com.example.adabank.feature_ababank.domain.useCases

data class TransactionUseCase(
    val createTransaction: CreateTransaction,
    val getTransactions: GetTransactions
) {
}