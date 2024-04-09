package com.example.adabank.feature_ababank.domain.useCases

import com.example.adabank.feature_ababank.domain.model.TransactionData
import com.example.adabank.feature_ababank.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow

class GetTransactions(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(): Flow<List<TransactionData>> {
        return transactionRepository.get()
    }
}