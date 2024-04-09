package com.example.adabank.feature_ababank.presentation.Fingerprint

import android.media.MediaRouter2.RouteCallback
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor


@Composable
fun FingerPrintScreen(
    navController: NavController,
    viewModel: FingerprintViewModel = hiltViewModel()
) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Background2Color)) {

        Spacer(modifier = Modifier.height(28.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription =null,
            Modifier
                .padding(start = 24.dp)
                .clickable { navController.popBackStack() }
        )
        Spacer(modifier = Modifier.height(48.dp))
        Column(Modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
            Image(painter = painterResource(id = R.drawable.ic_fingerprint), contentDescription =null)
            Column() {
                Text(text = "Use Touch ID to Authorise Paymentts",
                style = MaterialTheme.typography.titleLarge)
                
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Active touch ID so you don’t need to confirm your PIN every time you want to send money",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight(400)
                    ))
            }
            
            CustomAuthButton(
                text = "FINISH",
                state = true) {
                navController.navigate(Route.NavigationHome.route)
            }

            CustomAuthButton(
                text = "SKIP",
                state = true,
            modifier = Modifier.alpha(0.5f)) {
                navController.navigate(Route.NavigationHome.route)
            }
        }

    }
}