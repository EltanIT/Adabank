package com.example.adabank.feature_ababank.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.adabank.feature_ababank.presentation.navgraph.MainNavController
import com.example.adabank.ui.theme.AdabankTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AdabankTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                val viewModel: MainActivityViewModel = hiltViewModel()
                val startDestination = viewModel.startDestination.value
                val startGraphDestination = viewModel.startGraphDestination.value

                val navController = rememberNavController()
                if (startGraphDestination.isNotEmpty()){
                    MainNavController(
                        navHostController = navController,
                        startDestination = startDestination,
                        startGraphDestination = startGraphDestination
                    )
                }

            }
        }
    }
}
