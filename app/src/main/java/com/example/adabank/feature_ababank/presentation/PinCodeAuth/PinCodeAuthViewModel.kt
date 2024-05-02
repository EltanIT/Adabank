package com.example.adabank.feature_ababank.presentation.PinCodeAuth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.useCases.PinCodeUseCase
import com.example.adabank.feature_ababank.domain.useCases.biometric.BiometricUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PinCodeAuthViewModel @Inject constructor(
    private val pinCodeUseCase: PinCodeUseCase,
    private val biometricUseCase: BiometricUseCase
): ViewModel() {


    private val _state = mutableStateOf(PinCodeAuthState())
    val state: State<PinCodeAuthState> = _state


    private var isChecking = false


    init {
        checkBiometricState()
    }

    private fun checkBiometricState() {
        viewModelScope.launch(Dispatchers.IO) {
            val isSaved = biometricUseCase.getBiometricState()
            isSaved?.let {
                if (isSaved){
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            fingerprintIsOpen = true,
                            fingerprintIsSupport = true,
                        )
                    }
                }else{
                    withContext(Dispatchers.Main){
                        _state.value = state.value.copy(
                            fingerprintIsOpen = false,
                            fingerprintIsSupport = false,
                        )
                    }
                }
            }
        }
    }


    fun onEvent(event: PinCodeAuthEvent) {
        when(event){
            PinCodeAuthEvent.DeleteCode -> {
                if (!isChecking){
                    _state.value = state.value.copy(
                        code = state.value.code.dropLast(1)
                    )
                }
            }
            is PinCodeAuthEvent.EnteredCode -> {
                if (!isChecking){
                    _state.value = state.value.copy(
                        code = state.value.code+event.value,
                    )
                }
                if (state.value.code.length == 4){
                    isChecking = true
                    viewModelScope.launch(Dispatchers.IO) {
                        checkPinCode()
                    }

                }
            }
            PinCodeAuthEvent.Success -> {
                _state.value = state.value.copy(
                    successful = true
                )
            }

            is PinCodeAuthEvent.SetException -> {
                _state.value = state.value.copy(
                    exception = event.value
                )
            }

            PinCodeAuthEvent.OpenFingerprint -> {
                _state.value = state.value.copy(
                    fingerprintIsOpen = !state.value.fingerprintIsOpen
                )
            }
        }
    }


    private suspend fun checkPinCode(){
        val savedPinCode = pinCodeUseCase.readPinCode()
        savedPinCode?.let {
            if (savedPinCode.equals(state.value.code)){
                _state.value = state.value.copy(
                    successful = true
                )
            }else{
                _state.value = state.value.copy(
                    code = "",
                    exception = "Неверный код"
                )
                isChecking = false
            }
        }
    }

}