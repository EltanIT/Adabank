package com.example.adabank.feature_ababank.presentation.NavigationMainScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.adabank.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NavigationViewModel @Inject constructor(

): ViewModel() {

    private val _selectedItem = mutableStateOf(0)
    val selectedItem: State<Int> = _selectedItem

    val navigationItems = listOf(
        R.drawable.ic_home,
        R.drawable.ic_graph,
        R.drawable.ic_wallet,
        R.drawable.ic_notification
    )


    fun onEvent(event: NavigationEvent){
        when(event){
            NavigationEvent.ClickScan -> {

            }
            is NavigationEvent.SelectItem -> {
                _selectedItem.value = event.index
            }
        }

    }
}