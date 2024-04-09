package com.example.adabank.feature_ababank.presentation.Card

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.R
import com.example.adabank.feature_ababank.domain.useCases.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CardViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
): ViewModel() {


    private val _state = mutableStateOf(CardState())
    val state: State<CardState> = _state

    init {
        getCardInfo()
    }

    private fun getCardInfo() {
        viewModelScope.launch {
            try {
                val balance = profileUseCase.getProfileBalance()
                val card = profileUseCase.getProfileCard()

                _state.value = state.value.copy(
                    balance = balance,
                    card = card
                )
            }catch (e: Exception){
                Log.i("supabaseClient", e.message.toString())
            }
        }
    }

    val actionList = listOf(
        ActionItem(
            R.drawable.ic_lock_card,
            "Lock Card"
        ),
        ActionItem(
            R.drawable.ic_change_pin,
            "Change PIN"
        ),
        ActionItem(
            R.drawable.ic_top_up,
            "Top Up"
        ),
    )

    fun onEvent(event: CardEvent){
        when(event){
            CardEvent.isCardLimitEnable -> {
                _state.value = state.value.copy(
                    isCardLimitEnable = !state.value.isCardLimitEnable
                )
            }
        }
    }

}