package com.example.adabank.feature_ababank.presentation.MenuScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.core.presentation.components.SearchTextField
import com.example.adabank.feature_ababank.presentation.MenuScreen.components.MenuItemCompose
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor


@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MenuViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(BackgroundColor)) {
        CustomTopAppBar(title = "Menu") {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(36.dp))

        SearchTextField(
            value = state.search,
            hilt ="Search Menu",
            modifier = Modifier.padding(horizontal = 24.dp),
            onValueChangeListener = {viewModel.onEvent(MenuEvent.EnteredSearch(it))}) {
            viewModel.onEvent(MenuEvent.SearchClick)
        }
        Spacer(modifier = Modifier.height(48.dp))
        Text(text = "Shortcuts",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White,
                fontWeight = FontWeight(500)
            ),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)) {
            repeat(state.shortcuts.size){
                MenuItemCompose(
                    title = state.shortcuts[it].title,
                    icon =state.shortcuts[it].icon,
                    modifier = Modifier.clickable {
                        when(it){
                            0 -> {navController.navigate(Route.Transfer.route)}
                            1 -> {navController.navigate(Route.TopUpWallet.route)}
                            2 -> {}
                            3 -> {navController.navigate(Route.CodeQr.route)}
                        }
                    })
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 24.dp)
                .background(Color.White.copy(alpha = 0.1f))
        )
        Spacer(modifier = Modifier.height(36.dp))


        Text(text = "Other Menu",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White,
                fontWeight = FontWeight(500)
            ),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(28.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)) {
            repeat(state.otherMenu.size){
                MenuItemCompose(
                    title = state.otherMenu[it].title,
                    icon =state.otherMenu[it].icon,
                    modifier = Modifier.clickable {
                        when(it){
                            0 -> {navController.popBackStack()}
                            1 -> {}
                            2 -> {}
                            3 -> {}
                            4 -> {}
                            5 -> {navController.navigate(Route.ProfileSettings.route)}
                        }
                    })
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        Spacer(modifier = Modifier.height(157.dp))

    }
}