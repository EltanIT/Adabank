package com.example.adabank.feature_ababank.presentation.NavigationMainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.adabank.R
import com.example.adabank.feature_ababank.presentation.navgraph.BottomNavigationNavGraph
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.PrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(
    mainNavController: NavController,
    startDestination: String = Route.Home.route,
    viewModel: NavigationViewModel = hiltViewModel()
) {

    val navigationList = viewModel.navigationItems
    val selectedItem = viewModel.selectedItem.value

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            Box(Modifier
                .fillMaxWidth()
            ) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .blur(50.dp)
                    .padding(start = 12.dp, end = 12.dp, bottom=12.dp)
                    .align(Alignment.BottomCenter),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        5.dp
                    ),
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(Modifier.weight(1f),
                            horizontalArrangement = Arrangement.SpaceEvenly) {
                            repeat(2){
                                Icon(
                                    painter = painterResource(id = navigationList[it]),
                                    contentDescription = null,
                                    tint = if (selectedItem==it) PrimaryColor else Color.Black,
                                    modifier= Modifier
                                        .alpha(if (selectedItem == it) 1f else 0.12f)
                                        .clickable {
                                            viewModel.onEvent(NavigationEvent.SelectItem(it))
                                            when(it){
                                                0 -> navController.navigate(Route.Home.route){
                                                    navController.graph.startDestinationRoute?.let { route ->
                                                        popUpTo(route) {
                                                            saveState = true
                                                        }
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                                1 -> navController.navigate(Route.Graph.route){
                                                    navController.graph.startDestinationRoute?.let { route ->
                                                        popUpTo(route) {
                                                            saveState = true
                                                        }
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }

                                        }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(48.dp))

                        Row(Modifier.weight(1f),
                            horizontalArrangement = Arrangement.SpaceEvenly) {
                            repeat(2){
                                Icon(
                                    painter = painterResource(id = navigationList[it+2]),
                                    contentDescription = null,
                                    tint = if (selectedItem==it+2) PrimaryColor else Color.Black,
                                    modifier= Modifier
                                        .alpha(if (selectedItem == it + 2) 1f else 0.12f)
                                        .clickable {
                                            viewModel.onEvent(NavigationEvent.SelectItem(it + 2))
                                            when(it+2){
                                                2 -> navController.navigate(Route.Wallet.route){
                                                    navController.graph.startDestinationRoute?.let { route ->
                                                        popUpTo(route) {
                                                            saveState = true
                                                        }
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                                3 -> navController.navigate(Route.Notification.route){
                                                    navController.graph.startDestinationRoute?.let { route ->
                                                        popUpTo(route) {
                                                            saveState = true
                                                        }
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }

                                        })
                            }
                        }


                    }
                }

                FloatingActionButton(onClick = {mainNavController.navigate(Route.CodeOrScan.route)},
                    shape = RoundedCornerShape(20.dp),
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 5.dp,
                        focusedElevation = 5.dp
                    ),
                    containerColor = PrimaryColor,
                    modifier = Modifier
                        .size(48.dp)
                        .offset(y = (-28).dp)
                        .align(Alignment.Center)
//                        .padding(bottom = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_scan),
                        contentDescription = null)
                }
            }

        }
    ) {
        it
        BottomNavigationNavGraph(
            navHostController = navController,
            mainNavController = mainNavController,
            startDestination = startDestination
        )
    }
}