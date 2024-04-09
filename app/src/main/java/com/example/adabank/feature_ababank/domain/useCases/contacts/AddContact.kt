package com.example.adabank.feature_ababank.domain.useCases.contacts

import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.repository.ContactsRepository

class AddContact(
    private val contactsRepository: ContactsRepository
) {

    suspend operator fun invoke(contactData: ContactData){
        contactsRepository.addContact(contactData)
    }
}