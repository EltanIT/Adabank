package com.example.adabank.feature_ababank.domain.model

import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class ReceiptData(
    val total: String = "",
    val recipient_id: String = "",
    val created_at: String = LocalDateTime.now().toString(),
    val transaction_id: String = "",
    val category: String = "Equipment",
    )