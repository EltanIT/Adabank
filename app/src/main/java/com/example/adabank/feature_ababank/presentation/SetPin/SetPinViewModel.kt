package com.example.adabank.feature_ababank.presentation.SetPin

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.repository.SignInRepository
import com.example.adabank.feature_ababank.domain.useCases.PinCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SetPinViewModel @Inject constructor(
    private val pinCodeUseCase: PinCodeUseCase
): ViewModel() {

    private val _setPinState = mutableStateOf(SetPinState())
    val setPinState: State<SetPinState> = _setPinState


    fun onEvent(event: SetPinEvent){
        when(event){
            is SetPinEvent.EnteredCode -> {
                if (setPinState.value.code.length<4){
                    _setPinState.value = setPinState.value.copy(
                        code = setPinState.value.code+event.value,
                        stateNext = false
                    )
                    if (setPinState.value.code.length==4){
                        _setPinState.value = setPinState.value.copy(
                            stateNext = true
                        )
                    }
                }
            }
            SetPinEvent.DeleteCode -> {
                _setPinState.value = setPinState.value.copy(
                    code = setPinState.value.code.dropLast(1),
                    stateNext = false
                )
            }

            SetPinEvent.SavePinCode -> {
                viewModelScope.launch {
                    try {
                        pinCodeUseCase.savePinCode(setPinState.value.code)
                    }catch (e: Exception){
                        Log.i("datastoreException", e.message.toString())
                    }

                }

            }
        }
    }

}