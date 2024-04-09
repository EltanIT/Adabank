package com.example.adabank.feature_ababank.domain.repository

import com.example.adabank.feature_ababank.domain.model.ReceiptData
import com.example.adabank.feature_ababank.domain.model.TransactionData
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun create(data: ReceiptData)
    suspend fun get(user_id: String = ""): Flow<List<TransactionData>>
}