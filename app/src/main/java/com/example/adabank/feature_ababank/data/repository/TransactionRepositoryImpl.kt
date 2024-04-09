package com.example.adabank.feature_ababank.data.repository

import android.util.Log
import com.example.adabank.feature_ababank.data.TransactionDao
import com.example.adabank.feature_ababank.data.network.SupabaseInit.client
import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.model.ReceiptData
import com.example.adabank.feature_ababank.domain.model.TransactionData
import com.example.adabank.feature_ababank.domain.repository.TransactionRepository
import com.google.gson.GsonBuilder
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
): TransactionRepository {

    override suspend fun create(data: ReceiptData) {
        Log.i("supabaseClient", "roomSave")
        transactionDao.addTransaction(
            TransactionData(
                total = data.total,
                created_at = data.created_at,
                name = data.category,
                user_id = data.recipient_id,
                description = "Laptop Acer aspire 5"
        ))
        client.postgrest["transaction"].insert(data)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun get(user_id: String): Flow<List<TransactionData>>  {
        val transactionListDB = transactionDao.getTransactions()
        val list = transactionListDB.first()
        if (list.isEmpty()){
            try {
                val transactionsList = getTransactionFromSupabase()
                GlobalScope.launch(Dispatchers.IO) {
                    for (i in transactionsList){
                        transactionDao.addTransaction(i)
                    }
                }
                return flow {
                    emit(transactionsList)
                }
            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }

        }

        GlobalScope.launch(Dispatchers.IO) {
            try {
                transactionDao.deleteTransactions()
                for (i in getTransactionFromSupabase()){
                    transactionDao.addTransaction(i)
                }

            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }
        }
        return transactionListDB
    }

    private suspend fun getTransactionFromSupabase(): List<TransactionData> {
        val data = client.postgrest["transaction"].select{
            filter {
                eq("user_id", client.auth.currentUserOrNull()?.id?:"")
            }
            order("created_at", Order.DESCENDING)
        }.data

        Log.i("supabaseClient", data)

        val gson = GsonBuilder().create()
        return gson.fromJson(data, Array<TransactionData>::class.java).toList()
    }
}