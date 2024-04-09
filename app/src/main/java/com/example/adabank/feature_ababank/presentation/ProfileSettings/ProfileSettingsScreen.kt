package com.example.adabank.feature_ababank.presentation.ProfileSettings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.feature_ababank.presentation.MenuScreen.components.MenuItemCompose
import com.example.adabank.feature_ababank.presentation.ProfileSettings.components.CustomSettingItem
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.BackgroundColor


@Composable
fun ProfileSettingsScreen(
    navController: NavController,
    viewModel: ProfileSettingsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)) {
        CustomTopAppBar(title = "settings") {
            navController.popBackStack()
        }
        Column(
            Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Shortcuts",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight(500)
                )
            )
            Spacer(modifier = Modifier.height(28.dp))
            repeat(viewModel.listSettingsSecurity.size){
                CustomSettingItem(
                    title = viewModel.listSettingsSecurity[it].title,
                    icon = viewModel.listSettingsSecurity[it].icon,
                    isEnabled = if (it==3) state.turnOffCard else null,
                    onSwitchClickListener = {
                                            viewModel.onEvent(ProfileSettingsEvent.TurnOffCardClick)
                    },
                    modifier = Modifier.clickable {
                        when(it){
                            0 -> {navController.navigate(Route.ChangePin.route)}
                            1 -> {}
                            2 -> {}
                        }
                    })
                Spacer(modifier = Modifier.height(32.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Language",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight(500)
                )
            )
            Spacer(modifier = Modifier.height(28.dp))

            repeat(viewModel.listSettingsLanguage.size){
                CustomSettingItem(
                    title = viewModel.listSettingsLanguage[it].title,
                    icon = viewModel.listSettingsLanguage[it].icon,
                    modifier = Modifier.clickable {
                        when(it){
                            0 -> {}
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(text = "Other",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight(500)
                )
            )

            Spacer(modifier = Modifier.height(28.dp))

            repeat(viewModel.listSettingsOther.size){
                CustomSettingItem(
                    title = viewModel.listSettingsOther[it].title,
                    icon = viewModel.listSettingsOther[it].icon,
                    isEnabled = state.darkTheme,
                    onSwitchClickListener = {viewModel.onEvent(ProfileSettingsEvent.DarkThemeClick)},
                )
            }

            Spacer(modifier = Modifier.height(44.dp))
        }
    }
}