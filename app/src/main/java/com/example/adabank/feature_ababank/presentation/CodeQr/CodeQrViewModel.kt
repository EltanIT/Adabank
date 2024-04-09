package com.example.adabank.feature_ababank.presentation.CodeQr

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.feature_ababank.domain.useCases.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CodeQrViewModel @Inject constructor(
    private val getProfileUseCase: ProfileUseCase
): ViewModel() {


    private val _state = mutableStateOf(CodeQrState())
    val state: State<CodeQrState> = _state


    init {
        getProfile()
    }

    private fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val name = getProfileUseCase.getProfileName()
                val image = getProfileUseCase.getProfileImage()
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        name = name,
                        image = image
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


}