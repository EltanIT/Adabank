package com.example.adabank.feature_ababank.domain.useCases

import com.example.adabank.feature_ababank.domain.model.ReceiptData
import com.example.adabank.feature_ababank.domain.repository.TransactionRepository

class CreateTransaction(
    private val transactionRepository: TransactionRepository
) {

    suspend operator fun invoke(data: ReceiptData){
        transactionRepository.create(data)
    }
}