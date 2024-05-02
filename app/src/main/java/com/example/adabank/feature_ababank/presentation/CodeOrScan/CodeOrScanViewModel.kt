package com.example.adabank.feature_ababank.presentation.CodeOrScan

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class CodeOrScanViewModel @Inject constructor(

): ViewModel() {

    private val _state: MutableStateFlow<CodeOrScanState> = MutableStateFlow(CodeOrScanState())
    val state: StateFlow<CodeOrScanState> = _state


    fun onEvent(event: CodeOrScanEvent){
        when(event){
            is CodeOrScanEvent.OnCodeDetected -> {
                _state.update { it.copy(result = event.value) }
            }
            is CodeOrScanEvent.OnTargetPositioned -> {
                _state.update { it.copy(targetRect = event.value) }
            }
        }
    }

}