package com.example.adabank.feature_ababank.presentation.SetPin

sealed class SetPinEvent() {
    data class EnteredCode(val value: String) : SetPinEvent()
    object DeleteCode: SetPinEvent()
    object SavePinCode: SetPinEvent()
}
