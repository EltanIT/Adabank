package com.example.adabank.feature_ababank.presentation.Receipt

import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.model.ReceiptData

data class ReceiptState(
    val receiptData: ReceiptData = ReceiptData(),
    val contactData: ContactData = ContactData(),
)