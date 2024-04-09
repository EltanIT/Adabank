package com.example.adabank.feature_ababank.presentation.Receipt

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.model.ReceiptData
import com.example.adabank.feature_ababank.domain.useCases.TransactionUseCase
import com.example.adabank.feature_ababank.domain.useCases.contacts.ContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class ReceiptViewModel @Inject constructor(
    private val transactionUseCase: TransactionUseCase,
    private val contactsUseCase: ContactsUseCase
): ViewModel() {

    private val _state = mutableStateOf(ReceiptState())
    val state: State<ReceiptState> = _state


    fun setData(data: ReceiptData, contact: ContactData){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = state.value.copy(
                receiptData = data.copy(
                    transaction_id = UUID.randomUUID().toString()
                ),
                contactData = contact
            )
            createTransaction()
            addRecentContact()
        }

    }

    private suspend fun createTransaction() {
        transactionUseCase.createTransaction(state.value.receiptData)
    }

    private suspend fun addRecentContact() {
        contactsUseCase.addContact(state.value.contactData)
    }


}