package com.example.adabank.feature_ababank.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adabank.feature_ababank.domain.model.TransactionData
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {


    @Query("SELECT * FROM transactions")
    fun getTransactions(): Flow<List<TransactionData>>

    @Insert
    fun addTransaction(transactionData: TransactionData)

    @Query("DELETE FROM transactions")
    fun deleteTransactions()

}