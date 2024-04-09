package com.example.adabank.feature_ababank.presentation.SpashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.BackgroundColor


@Composable
fun SplashScreen(
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)) {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash_screen_back),
                contentDescription = null)
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 35.dp)
                    .align(Alignment.Center),
//                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.ic_splash_screen_image), contentDescription = null)
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            Modifier
                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                .background(Color.White, RoundedCornerShape(30.dp))
                .border(3.dp, Color(0xffF6F6F6), RoundedCornerShape(30.dp))
                )
        {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Start Payments Easily \nIn The Digital Age",
                    style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier
                        .padding(horizontal = 38.dp)
                        .alpha(0.5f),
                    text = "Payment tool that is easy and fast to use in this easy-to-use digital era. Use the features that make your business easier",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(46.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_button_next),
                    contentDescription = null,
                    modifier = Modifier.clickable { navController.navigate(Route.Login.route)}
                )
            }

        }
    }

}
