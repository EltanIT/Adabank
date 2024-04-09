package com.example.adabank.feature_ababank.presentation.TopUpCard

sealed class TopUpCardEvent {
    data class SliderMove(val value: Float): TopUpCardEvent()

}