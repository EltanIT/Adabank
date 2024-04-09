package com.example.adabank.feature_ababank.presentation.NavigationMainScreen

sealed class NavigationEvent {
    data class SelectItem(val index: Int): NavigationEvent()
    object ClickScan: NavigationEvent()
}