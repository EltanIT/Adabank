package com.example.adabank.feature_ababank.presentation.ChangePin

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.useCases.PinCodeUseCase
import com.example.adabank.feature_ababank.domain.useCases.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChangePinViewModel @Inject constructor(
    private val pinCodeUseCase: PinCodeUseCase,
    private val profileUseCase: ProfileUseCase
): ViewModel() {

    private val _state = mutableStateOf(ChangePinState())
    val state: State<ChangePinState> = _state


    fun onEvent(event: ChangePinEvent){
        when(event){
            ChangePinEvent.ChangeVisibleConfirmNewPin -> {
                _state.value = state.value.copy(
                    isVisibleConfirmNewPin = !state.value.isVisibleConfirmNewPin
                )
            }
            ChangePinEvent.ChangeVisibleNewPin -> {
                _state.value = state.value.copy(
                    isVisibleNewPin = !state.value.isVisibleNewPin
                )
            }
            is ChangePinEvent.EnteredConfirmNewPin -> {
                _state.value = state.value.copy(
                    confirmNewPin = event.value,
                    isSame = event.value.equals(state.value.newPin)
                )
            }
            is ChangePinEvent.EnteredNewPin -> {
                _state.value = state.value.copy(
                    newPin = event.value,
                    isSame = event.value.equals(state.value.confirmNewPin)
                )
            }

            ChangePinEvent.SaveNewPinCode -> {
                viewModelScope.launch {
                    try {
                        pinCodeUseCase.savePinCode(state.value.newPin)
                        _state.value = state.value.copy(
                            exception = "",
                            isSaved = true,
                            newPin = "",
                            confirmNewPin = ""
                        )
                    }catch (e: Exception){
                        Log.i("supabaseClient", e.message.toString())
                    }
                }
            }
        }
    }

    fun setBalance(s: String) {
        if (s.isNotEmpty()){
            _state.value = state.value.copy(
                balance = s
            )
        }else{
            getBalance()
        }

    }

    private fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val balance = profileUseCase.getProfileBalance()
                _state.value = state.value.copy(
                    balance = balance,
                )
            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }
        }
    }

}