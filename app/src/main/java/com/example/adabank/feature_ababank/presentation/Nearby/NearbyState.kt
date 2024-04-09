package com.example.adabank.feature_ababank.presentation.Nearby

import com.example.adabank.feature_ababank.domain.model.ContactData

data class NearbyState(
    val contacts: List<ContactData> = emptyList(),
    val isSearching: Boolean = true
)