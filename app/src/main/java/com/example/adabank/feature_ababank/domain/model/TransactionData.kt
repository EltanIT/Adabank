package com.example.adabank.feature_ababank.domain.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable



@Entity(tableName = "transactions")
@Serializable
data class TransactionData(
    val name: String = "Equipment",
    val description: String = "Laptop Acer aspire 5",
    @DrawableRes val image: Int = 0,
    val total: String = "",
    val created_at: String = "",
    val transaction_id: String = "",

    val user_id: String = "",
    @PrimaryKey val id: Int? = null
)
