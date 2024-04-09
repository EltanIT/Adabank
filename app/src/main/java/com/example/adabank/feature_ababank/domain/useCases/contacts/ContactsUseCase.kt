package com.example.adabank.feature_ababank.domain.useCases.contacts

data class ContactsUseCase(
    val getAllContacts: GetAllContacts,
    val getRecentContacts: GetRecentContacts,
    val addContact: AddContact,
)