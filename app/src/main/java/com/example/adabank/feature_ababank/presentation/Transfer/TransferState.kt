package com.example.adabank.feature_ababank.presentation.Transfer

import com.example.adabank.feature_ababank.domain.model.ContactData

data class TransferState(
    val allContacts: List<ContactData> = emptyList(),
    val recentsContacts: List<ContactData> = emptyList(),

    val search: String = "",
    val selectContact: String = "1",
) {

    val transferTypeList = listOf(
        "Bank account",
        "Scan",
        "Nearby",

    )

}