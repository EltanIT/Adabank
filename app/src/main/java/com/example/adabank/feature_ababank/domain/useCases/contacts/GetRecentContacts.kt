package com.example.adabank.feature_ababank.domain.useCases.contacts

import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

class GetRecentContacts(
    private val contactsRepository: ContactsRepository
) {

    suspend operator fun invoke(): Flow<List<ContactData>> {
        return contactsRepository.getRecentContacts()
    }
}