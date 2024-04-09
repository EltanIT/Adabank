package com.example.adabank.feature_ababank.presentation.navgraph

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.adabank.feature_ababank.presentation.Card.CardScreen
import com.example.adabank.feature_ababank.presentation.Graph.GraphScreen
import com.example.adabank.feature_ababank.presentation.Home.HomeScreen
import com.example.adabank.feature_ababank.presentation.Notification.NotificationScreen
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun BottomNavigationNavGraph(
    navHostController: NavHostController,
    mainNavController: NavController,
    startDestination: String
) {
    val systemUiController = rememberSystemUiController()

    NavHost(
        navHostController,
        startDestination){
        composable(Route.Home.route){
            BackHandler(true) {}
            systemUiController.setStatusBarColor(color = BackgroundColor)
            HomeScreen(navController = mainNavController)
        }
        composable(Route.Graph.route){
            BackHandler(true) {}
            systemUiController.setStatusBarColor(color = BackgroundColor)
            GraphScreen(navController = mainNavController)
        }
        composable(Route.Wallet.route){
            BackHandler(true) {}
            systemUiController.setStatusBarColor(color = BackgroundColor)
            CardScreen(navController = mainNavController)
        }
        composable(Route.Notification.route){
            BackHandler(true) {}
            systemUiController.setStatusBarColor(color = Background2Color)
            NotificationScreen()
        }

    }
}