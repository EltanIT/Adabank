package com.example.adabank.feature_ababank.presentation.ProfileSettings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.adabank.R
import com.example.adabank.feature_ababank.presentation.MenuScreen.MenuItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileSettingsViewModel @Inject constructor(

): ViewModel() {

    val listSettingsSecurity = listOf(
        MenuItem(
            "Change PIN",
            R.drawable.ic_change_pin
        ),
        MenuItem(
            "Change Password",
            R.drawable.ic_change_password
        ),
        MenuItem(
            "Change fingerprint",
            R.drawable.ic_change_fingerprint
        ),
        MenuItem(
            "turn off card",
            R.drawable.ic_turn_off_card
        )
    )
    val listSettingsLanguage = listOf(
        MenuItem(
            "Change Language",
            R.drawable.ic_change_language
        )
    )
    val listSettingsOther = listOf(
        MenuItem(
            "Dark Theme",
            R.drawable.ic_dark_theme
        )
    )

    private val _state = mutableStateOf(ProfileSettingsState())
    val state: State<ProfileSettingsState> = _state


    fun onEvent(event: ProfileSettingsEvent){
        when(event){
            ProfileSettingsEvent.DarkThemeClick -> {
                _state.value = state.value.copy(
                    darkTheme = !state.value.darkTheme

                )
            }
            ProfileSettingsEvent.TurnOffCardClick -> {
                _state.value = state.value.copy(
                    turnOffCard = !state.value.turnOffCard
                )
            }
        }
    }
}