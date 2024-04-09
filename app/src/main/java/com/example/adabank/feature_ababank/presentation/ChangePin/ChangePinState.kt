package com.example.adabank.feature_ababank.presentation.ChangePin

data class ChangePinState(
    val newPin: String = "",
    val confirmNewPin: String = "",
    val balance: String = "",
    val exception: String = "",
    val isSame: Boolean = false,
    val isSaved: Boolean = false,


    val isVisibleNewPin: Boolean = false,
    val isVisibleConfirmNewPin: Boolean = false,

)
