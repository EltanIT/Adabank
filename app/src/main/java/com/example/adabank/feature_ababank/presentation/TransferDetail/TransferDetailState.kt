package com.example.adabank.feature_ababank.presentation.TransferDetail

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.adabank.feature_ababank.domain.model.ContactData

data class TransferDetailState(
    val amount: String = "",
    val coordinate: Dp = 0.dp,
    val contact: ContactData = ContactData(),
    val stateSwap: Boolean = false
) {
}