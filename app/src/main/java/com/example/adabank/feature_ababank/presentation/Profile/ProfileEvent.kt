package com.example.adabank.feature_ababank.presentation.Profile

sealed class ProfileEvent {
    object OnlinePaymentClick: ProfileEvent()
    object AtmWindrawalsClick: ProfileEvent()
    object PaymentAbroadClick: ProfileEvent()
    object OpenProfileDetails: ProfileEvent()


}