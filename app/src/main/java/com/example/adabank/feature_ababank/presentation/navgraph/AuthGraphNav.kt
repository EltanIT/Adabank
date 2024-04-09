package com.example.adabank.feature_ababank.presentation.navgraph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.adabank.feature_ababank.presentation.Fingerprint.FingerPrintScreen
import com.example.adabank.feature_ababank.presentation.Login.LoginScreen
import com.example.adabank.feature_ababank.presentation.SetPin.SetPinScreen
import com.example.adabank.feature_ababank.presentation.SpashScreen.SplashScreen
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


fun NavGraphBuilder.authGraphNav(
    navController: NavController,
    startDestination: String,
    systemUiController: SystemUiController
) {

    navigation(startDestination, Route.AuthGraph.route){
        composable(Route.SplashScreen.route){
            systemUiController.setStatusBarColor(
                color = BackgroundColor
            )
            SplashScreen(navController = navController)
        }
        composable(Route.Login.route){
            systemUiController.setStatusBarColor(
                color = Background2Color
            )
            LoginScreen(navController = navController)
        }
        composable(Route.SetPin.route){
            SetPinScreen(navController = navController)
        }
        composable(Route.FingerPrint.route){
            FingerPrintScreen(navController = navController)
        }
    }
}