package com.example.adabank.feature_ababank.presentation.SetPin

sealed class SetPinEvent() {
    data class EnteredCode(val value: String) : SetPinEvent()
    data object DeleteCode: SetPinEvent()
    data object SavePinCode: SetPinEvent()
}
