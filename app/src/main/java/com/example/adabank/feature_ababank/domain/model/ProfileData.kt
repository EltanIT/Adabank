package com.example.adabank.feature_ababank.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class ProfileData(
    val user_id: String = "",

    val name: String = "",
    val image: String = "",
    val balance: String = "",
    val castag: String = "",
    val accountNumber: String = "",
    val email: String = "",
    val card: String = "",
    val number: String = "",
    val id_no: String = "",
    val npwp: String = "",
)
