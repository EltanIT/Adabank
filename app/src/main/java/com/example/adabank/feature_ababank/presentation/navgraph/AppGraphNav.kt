@file:OptIn(ExperimentalLayoutApi::class)

package com.example.adabank.feature_ababank.presentation.navgraph

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.adabank.R
import com.example.adabank.feature_ababank.domain.model.ContactData
import com.example.adabank.feature_ababank.domain.model.ReceiptData
import com.example.adabank.feature_ababank.presentation.ChangePin.ChangePinScreen
import com.example.adabank.feature_ababank.presentation.ChangePin.ChangePinViewModel
import com.example.adabank.feature_ababank.presentation.CodeOrScan.CodeOrScanScreen
import com.example.adabank.feature_ababank.presentation.CodeQr.CodeQrScreen
import com.example.adabank.feature_ababank.presentation.GraphDetails.GraphDetailsScreen
import com.example.adabank.feature_ababank.presentation.MenuScreen.MenuScreen
import com.example.adabank.feature_ababank.presentation.NavigationMainScreen.NavigationScreen
import com.example.adabank.feature_ababank.presentation.Nearby.NearbyScreen
import com.example.adabank.feature_ababank.presentation.Nearby.NearbyViewModel
import com.example.adabank.feature_ababank.presentation.Profile.ProfileScreen
import com.example.adabank.feature_ababank.presentation.ProfileSettings.ProfileSettingsScreen
import com.example.adabank.feature_ababank.presentation.Receipt.ReceiptScreen
import com.example.adabank.feature_ababank.presentation.Receipt.ReceiptViewModel
import com.example.adabank.feature_ababank.presentation.TopUpCard.TopUpCardScreen
import com.example.adabank.feature_ababank.presentation.TopUpCard.TopUpCardViewModel
import com.example.adabank.feature_ababank.presentation.Transfer.TransferScreen
import com.example.adabank.feature_ababank.presentation.Transfer.TransferViewModel
import com.example.adabank.feature_ababank.presentation.TransferDetail.TransferDetailScreen
import com.example.adabank.feature_ababank.presentation.TransferDetail.TransferDetailViewModel
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.gson.Gson


fun NavGraphBuilder.appGraphNav(
    navHostController: NavController,
    startDestination: String,
    systemUiController: SystemUiController
) {

    navigation(startDestination, Route.AppGraph.route){

        composable(Route.NavigationHome.route){

            BackHandler(true) {}
            systemUiController.setStatusBarColor(
                color = BackgroundColor
            )
            NavigationScreen(
                mainNavController = navHostController
            )
            navHostController.enableOnBackPressed(false)
        }
        composable(Route.Menu.route){
            systemUiController.setStatusBarColor(color = BackgroundColor)
            MenuScreen(
                navController = navHostController
            )
        }

        composable(Route.Transfer.route){
            systemUiController.setStatusBarColor(color = BackgroundColor)

            val viewModel: TransferViewModel = hiltViewModel()

            TransferScreen(
                navController = navHostController,
                viewModel = viewModel
            )
        }

        composable(Route.TransferDetail.route){
            systemUiController.setStatusBarColor(color = BackgroundColor)
            val viewModel: TransferDetailViewModel = hiltViewModel()
            val contact = it.arguments?.getString("contact")
            viewModel.setContact(Gson().fromJson(contact, ContactData::class.java))

            TransferDetailScreen(
                navController = navHostController,
                viewModel = viewModel
            )
        }

        composable(Route.Nearby.route){
            systemUiController.setStatusBarColor(color = Background2Color)
            val viewModel: NearbyViewModel = hiltViewModel()
            NearbyScreen(
                navController = navHostController,
                viewModel = viewModel
            )
        }

        composable(Route.CodeOrScan.route){
            systemUiController.setStatusBarColor(color = Background2Color)
            CodeOrScanScreen(
                navController = navHostController,
            )
        }

        composable(Route.CodeQr.route){
            systemUiController.setStatusBarColor(color = Background2Color)
            CodeQrScreen(
                navController = navHostController,
            )
        }

        composable(Route.ChangePin.route){
            systemUiController.setStatusBarColor(color = BackgroundColor)

            val viewModel: ChangePinViewModel = hiltViewModel()
            val balance = it.arguments?.getString("balance")
            viewModel.setBalance(balance?:"")

            ChangePinScreen(
                navController = navHostController,
                viewModel = viewModel
            )
        }

        composable(Route.TopUpCard.route){
            systemUiController.setStatusBarColor(color = BackgroundColor)

            val viewModel: TopUpCardViewModel = hiltViewModel()
            val balance = it.arguments?.getString("balance")
            viewModel.setBalance(balance?:"")

            TopUpCardScreen(
                navController = navHostController,
                viewModel = viewModel
            )
        }

        composable(Route.Receipt.route){
            systemUiController.setStatusBarColor(
                color = Background2Color
            )

            val viewModel: ReceiptViewModel = hiltViewModel()

            val data = it.arguments?.getString("receipt")
            val contact = it.arguments?.getString("contact")

            var isCreatingTransaction = remember{
                mutableStateOf(true)
            }

            LaunchedEffect(isCreatingTransaction.value){
                viewModel.setData(
                    Gson().fromJson(data, ReceiptData::class.java),
                    Gson().fromJson(contact, ContactData::class.java)
                )
//                isCreatingTransaction.value = false
            }

            ReceiptScreen(
                navController = navHostController,
                viewModel = viewModel
            )
        }

        composable(Route.Profile.route){
            systemUiController.setStatusBarColor(
                color = BackgroundColor
            )

            ProfileScreen(
                navController = navHostController
            )
        }

        composable(Route.GraphDetails.route){
            systemUiController.setStatusBarColor(
                color = BackgroundColor
            )

            GraphDetailsScreen(
                navController = navHostController
            )
        }

        composable(Route.ProfileSettings.route){
            systemUiController.setStatusBarColor(
                color = BackgroundColor
            )

            ProfileSettingsScreen(
                navController = navHostController
            )
        }


        composable(Route.TopUpWallet.route){
            systemUiController.setStatusBarColor(color = Background2Color)
            Box(Modifier.fillMaxSize().background(Background2Color)) {
                Image(painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 24.dp, top = 28.dp)
                        .clickable {
                            navHostController.popBackStack()
                        })
                Text(text = "Top-up Wallet",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Black,
                        fontWeight = FontWeight(500)
                    ),
                    modifier = Modifier
                        .padding(top = 28.dp)
                        .align(Alignment.TopCenter))
            }
        }
    }
}