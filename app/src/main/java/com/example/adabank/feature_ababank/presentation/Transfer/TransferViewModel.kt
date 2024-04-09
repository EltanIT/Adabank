package com.example.adabank.feature_ababank.presentation.Transfer

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.useCases.contacts.ContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransferViewModel @Inject constructor(
    private val contactsUseCase: ContactsUseCase
): ViewModel() {

    private val _state = mutableStateOf(TransferState())
    val state: State<TransferState> = _state

    private var allContacts = arrayListOf<ContactData>()

    init {
        getAllContacts()
        getRecentContacts()
    }

    private fun getRecentContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                contactsUseCase.getRecentContacts().onEach {
                    val list = it.reversed()
                    if (list.size>4){
                        val contactList = arrayListOf<ContactData>()
                        for (i in 0..3){
                            contactList.add(list[i])
                        }
                        _state.value = state.value.copy(
                            recentsContacts = contactList,
                            selectContact = contactList[0].number
                        )
                    }else if (list.isNotEmpty()){
                        _state.value = state.value.copy(
                            recentsContacts = list,
                            selectContact = list[0].number
                        )
                    }
                }.launchIn(viewModelScope)

            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }
        }
    }

    fun onEvent(event: TransferEvent){
        when(event){
            is TransferEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    search = event.value,
                    allContacts = if (event.value.isEmpty()) allContacts
                                  else allContacts.filter {
                                      it.name.lowercase().contains(event.value.lowercase())
                                  }
                )
            }
            is TransferEvent.SelectContact -> {
                _state.value = state.value.copy(
                    selectContact = event.id
                )
            }
        }
    }

    private fun getAllContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val contacts = contactsUseCase.getAllContacts()
                _state.value = state.value.copy(
                    allContacts = contacts,
                )
                allContacts.clear()
                allContacts.addAll(contacts)
                Log.i("supabaseClient", allContacts.size.toString())
            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }

        }
    }
}