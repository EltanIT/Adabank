package com.example.adabank.feature_ababank.domain.repository

import com.example.adabank.feature_ababank.domain.model.ContactData
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    suspend fun getAllContacts(): List<ContactData>
    suspend fun getRecentContacts(): Flow<List<ContactData>>
    suspend fun addContact(contactData: ContactData)
}