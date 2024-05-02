package com.example.adabank.feature_ababank.domain.useCases.contacts

import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.repository.ContactsRepository

class GetAllContacts(
    private val contactsRepository: ContactsRepository
) {

    suspend operator fun invoke(): List<ContactData>{
        val contacts = contactsRepository.getAllContacts()
        return contacts.distinctBy { it.number }
    }
}