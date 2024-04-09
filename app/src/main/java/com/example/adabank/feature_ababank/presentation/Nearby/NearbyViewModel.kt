package com.example.adabank.feature_ababank.presentation.Nearby

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.useCases.contacts.ContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NearbyViewModel @Inject constructor(
    private val contactsUseCase: ContactsUseCase
): ViewModel() {


    private val _state = mutableStateOf(NearbyState())
    val state: State<NearbyState> = _state

    init {
        timer()
        getContacts()
    }

    private fun getContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = contactsUseCase.getAllContacts()
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        contacts = data
                    )
                }
            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }
        }
    }

    fun onEvent(event: NearbyEvent){
        when(event){
            else -> {}
        }
    }


    private fun timer(){
        object: CountDownTimer(5000, 1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                _state.value = state.value.copy(
                    isSearching = false
                )
            }

        }.start()
    }


}