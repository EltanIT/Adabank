package com.example.adabank.feature_ababank.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.adabank.feature_ababank.domain.model.ContactData
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDao {


    @Query("SELECT * FROM contacts")
    fun getRecentContacts(): Flow<List<ContactData>>

    @Insert
    fun addRecentContact(contactData: ContactData)

    @Query("DELETE FROM contacts WHERE number = :number")
    fun deleteRecentContact(number: String)
}