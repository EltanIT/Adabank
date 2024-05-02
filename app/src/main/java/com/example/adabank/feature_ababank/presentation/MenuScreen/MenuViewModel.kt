package com.example.adabank.feature_ababank.presentation.MenuScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf(MenuState())
    val state: State<MenuState> = _state


    init {
        _state.value = state.value.copy(
            filteredOtherMenu = state.value.otherMenu,
            filteredShortcutMenu = state.value.shortcuts,
        )
    }

    fun onEvent(event: MenuEvent){
        when(event){
            is MenuEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    search = event.value,
                    filteredShortcutMenu = state.value.shortcuts.filter { it.title.lowercase().contains(event.value) },
                    filteredOtherMenu = state.value.otherMenu.filter { it.title.lowercase().contains(event.value) },
                )
            }

            MenuEvent.SearchClick -> {

            }
        }
    }
}