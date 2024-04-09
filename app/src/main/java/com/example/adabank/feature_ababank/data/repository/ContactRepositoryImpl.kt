package com.example.adabank.feature_ababank.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import com.example.adabank.feature_ababank.data.ContactDao
import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl(
    private val context: Context,
    private val contactDao: ContactDao
): ContactsRepository {
    @SuppressLint("Range")
    override suspend fun getAllContacts(): List<ContactData> {
        val contentResolver = context.contentResolver
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null, null)

        var list = arrayListOf<ContactData>()
        cursor?.let {
            if (cursor.count > 0){
                while (cursor.moveToNext()){
                    val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    val phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val image = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

                    if (!phone.contains("*") || !phone.contains("#")){
                        list.add(
                            ContactData(
                                name = name.ifEmpty { phone },
                                image = image,
                                number = phone
                            )
                        )
                    }

                }
                return list.reversed()
            }
        }
        return list
    }

    override suspend fun getRecentContacts(): Flow<List<ContactData>> {
        return contactDao.getRecentContacts()
    }

    override suspend fun addContact(contactData: ContactData) {
        contactDao.deleteRecentContact(contactData.number)
        contactDao.addRecentContact(contactData.copy(
            id = null
        ))
    }
}