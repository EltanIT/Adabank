package com.example.adabank.feature_ababank.presentation.MenuScreen

sealed class MenuEvent{
    data class EnteredSearch(val value: String): MenuEvent()

    object SearchClick: MenuEvent()
}
