package com.example.adabank.feature_ababank.presentation.Home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.R
import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.model.ProfileData
import com.example.adabank.feature_ababank.domain.useCases.ProfileUseCase
import com.example.adabank.feature_ababank.domain.useCases.TransactionUseCase
import com.example.adabank.feature_ababank.domain.useCases.contacts.ContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
    private val transactionUseCase: TransactionUseCase,
    private val contactsUseCase: ContactsUseCase
): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    val actionList = listOf(
        ActionItem(
            "Transfer",
            R.drawable.ic_transfer
        ),
        ActionItem(
            "Top-up",
            R.drawable.ic_top_up
        ),
        ActionItem(
            "Bill",
            R.drawable.ic_bill
        ),
        ActionItem(
            "More",
            R.drawable.ic_more
        ),
    )


    init {
        getProfile()
        getTransactions()
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
                            sendHistory = contactList
                        )
                    }else if (list.isNotEmpty()){
                        _state.value = state.value.copy(
                            sendHistory = list
                        )
                    }
                }.launchIn(viewModelScope)

            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }
        }
    }

    private fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val transactions = transactionUseCase.getTransactions()
                transactions.onEach {
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            transactionHistory = it.reversed()
                        )
                    }
                }.launchIn(viewModelScope)

            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val profileName = profileUseCase.getProfileName()
                val profileImage = profileUseCase.getProfileImage()
                val profileBalance = profileUseCase.getProfileBalance()
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        name = profileName,
                        image = profileImage,
                        balance = profileBalance,
                    )
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        exception = e.message.toString()
                    )
                }
                Log.i("supabaseClient", e.message.toString())
            }
        }
    }

    fun onEvent(){

    }
}