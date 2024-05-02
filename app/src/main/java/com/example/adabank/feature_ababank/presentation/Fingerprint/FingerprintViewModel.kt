package com.example.adabank.feature_ababank.presentation.Fingerprint

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.useCases.biometric.BiometricUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FingerprintViewModel @Inject constructor(
    private val biometricUseCase: BiometricUseCase
): ViewModel() {


    private val _state = mutableStateOf(FingerprintState())
    val state: State<FingerprintState> = _state


    fun onEvent(event: FingerprintEvent){
        when(event){
            is FingerprintEvent.SetException -> {
                _state.value = state.value.copy(
                    exception = event.value
                )
            }
            FingerprintEvent.ShowBiometricPrompt -> {
                _state.value = state.value.copy(
                    fingerprintPromptIsOpen = !state.value.fingerprintPromptIsOpen
                )
            }
            FingerprintEvent.Successful -> {
                viewModelScope.launch(Dispatchers.IO) {
                    biometricUseCase.saveBiometricState(true)
                    _state.value = state.value.copy(
                        isSuccess = true
                    )
                }

            }
        }
    }
}