package com.example.adabank.feature_ababank.presentation.ChangePin

sealed class ChangePinEvent {
    data class EnteredNewPin(val value: String): ChangePinEvent()
    data class EnteredConfirmNewPin(val value: String): ChangePinEvent()

    object ChangeVisibleNewPin: ChangePinEvent()
    object ChangeVisibleConfirmNewPin: ChangePinEvent()
    object SaveNewPinCode: ChangePinEvent()
}