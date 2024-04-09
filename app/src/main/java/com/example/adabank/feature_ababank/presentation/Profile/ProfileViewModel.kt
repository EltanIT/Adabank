package com.example.adabank.feature_ababank.presentation.Profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adabank.R
import com.example.adabank.feature_ababank.domain.useCases.GetPrimaryDeviceName
import com.example.adabank.feature_ababank.domain.useCases.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: ProfileUseCase,
): ViewModel() {

    private val getPrimaryDeviceName: GetPrimaryDeviceName = GetPrimaryDeviceName()

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    val listGeneral = listOf(
        SettingDataItem(
            R.drawable.ic_online_payment,
            "Online Payment",
            "Unblock PIN or CVV and more"
        ),
        SettingDataItem(
            R.drawable.ic_atm_windrawals,
            "ATM Windrawals",
            "Enable cash withdrawals"
        ),
        SettingDataItem(
            R.drawable.ic_payment_abroad,
            "Payment abroad",
            "Enable this card abroad"
        ),
    )

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.AtmWindrawalsClick -> {
                _state.value = state.value.copy(
                    atmWindrawals = !state.value.atmWindrawals,
                    setting = state.value.setting.copy(
                        atmWindrawals = !state.value.setting.atmWindrawals
                    )
                )
            }
            ProfileEvent.OnlinePaymentClick -> {
                _state.value = state.value.copy(
                    onlinePayment = !state.value.onlinePayment,
                    setting = state.value.setting.copy(
                        onlinePayment = !state.value.setting.onlinePayment
                    )
                )
            }
            ProfileEvent.PaymentAbroadClick -> {
                _state.value = state.value.copy(
                    paymentAbroad = !state.value.paymentAbroad,
                    setting = state.value.setting.copy(
                        paymentAbroad = !state.value.setting.paymentAbroad
                    )
                )
            }

            ProfileEvent.OpenProfileDetails -> {
                _state.value = state.value.copy(
                   isProfileDetailsOpen = !state.value.isProfileDetailsOpen
                )
            }
        }
    }


    init {
        getProfile()
        getDeviceName()
    }

    private fun getProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val profile = getProfileUseCase()
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        profile = profile
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

    private fun getDeviceName() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val deviceName = getPrimaryDeviceName()
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        primaryDevice = deviceName
                    )
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    _state.value = state.value.copy(
                        primaryDevice = "Redmi M2004"
                    )
                }

            }

        }

    }



}