package com.example.adabank.feature_ababank.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "contacts")
@Serializable
data class ContactData(
    val name: String = "",
    val image: String? = "",
    val bank: String = "0987 3422 8756",
    val number: String = "",
    @PrimaryKey val id: Int? = null
)