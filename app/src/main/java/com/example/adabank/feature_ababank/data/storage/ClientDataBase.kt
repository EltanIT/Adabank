package com.example.adabank.feature_ababank.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.model.TransactionData


@Database(
    entities = [TransactionData::class, ContactData::class],
    version = 3
)
abstract class ClientDataBase: RoomDatabase() {


    abstract val transactionDao: TransactionDao
    abstract val contactDao: ContactDao

    companion object{
        const val DATABASE_NAME = "clientDB"
    }

}