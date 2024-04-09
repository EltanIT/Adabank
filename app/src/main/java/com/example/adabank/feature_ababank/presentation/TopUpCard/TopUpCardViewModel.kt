package com.example.adabank.feature_ababank.presentation.TopUpCard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.roundToInt


@HiltViewModel
class TopUpCardViewModel @Inject constructor(

): ViewModel() {

    val listAmounts = listOf(
        "50.000",
        "75.000",
        "100.000",
        "150.000",
        "200.000",
        "250.000",
        "300.000",
        "350.000",

    )

    private val _state = mutableStateOf(TopUpCardState())
    val state: State<TopUpCardState> = _state


    fun onEvent(event: TopUpCardEvent){
        when(event){
            is TopUpCardEvent.SliderMove -> {
                _state.value = state.value.copy(
                    totalFloat = event.value,
                    total = ((event.value*100).roundToInt()/100.0).toString()
                )
            }
        }
    }

    fun setBalance(s: String) {
        _state.value = state.value.copy(
            balance = s
        )
    }

}