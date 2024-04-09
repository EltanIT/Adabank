package com.example.adabank.feature_ababank.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MainNavController(
    navHostController: NavHostController,
    startDestination: String,
    startGraphDestination: String
) {
    val systemUiController = rememberSystemUiController()

    NavHost(navHostController,startGraphDestination){
        authGraphNav(
            navController = navHostController,
            startDestination = startDestination,
            systemUiController = systemUiController
        )
        appGraphNav(
            navHostController = navHostController,
            startDestination = Route.NavigationHome.route,
            systemUiController = systemUiController
        )
    }

}