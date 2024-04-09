package com.example.adabank.feature_ababank.presentation.Nearby

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.feature_ababank.presentation.Transfer.components.ContactNearbyItem
import com.example.adabank.ui.theme.Background2Color


@Composable
fun NearbyScreen(
    navController: NavController,
    viewModel: NearbyViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(
        Modifier
            .fillMaxSize()
            .background(Background2Color)) {

        val infinitePolice  = rememberInfiniteTransition()
        val rotate by infinitePolice.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(5000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,

            )
        )

        if (state.isSearching){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 96.dp)
                    .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly) {
                Image(
                    painter = painterResource(id = R.drawable.ic_radar),
                    contentDescription = null,
                    modifier = Modifier.rotate(rotate)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Searching for people\n" +
                                "nearby..",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight(600)
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "To find your friends, ask them to\n" +
                                "open the screen in their app, or \n" +
                                "invite them to join",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(400),
                            color = Color.Black.copy(alpha = 0.5f)
                        )
                    )
                }
                
                CustomAuthButton(
                    text = "NEED HELP ?",
                    state = false,
                    isDimly = true,
                modifier = Modifier.padding(horizontal = 62.dp)) {
                    
                }
            }
        }
        else{
            Column(Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_map),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f),
                    contentScale = ContentScale.Crop
                )
                Column(Modifier.fillMaxSize()
                    .background(Background2Color,
                    RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)),
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Spacer(
                        modifier = Modifier
                            .width(20.dp)
                            .height(1.dp)
                            .background(Color.Black.copy(alpha = 0.1f))
                    )
                    Spacer(modifier = Modifier.height(28.dp))
                    Text(
                        text = "All Contacts",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight(500),
                            color = Color.Black.copy(alpha = 0.5f),
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        items(state.contacts.size){
                            ContactNearbyItem(
                                name = state.contacts[it].name,
                                distance = "300 metres",
                                image = state.contacts[it].image?:"",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp)) {
                                
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }

            }

        }

        CustomTopAppBar(
            title = "PayNearby",
            color = Color.Black,
            modifier = Modifier.align(Alignment.TopCenter)) {
            navController.popBackStack()
        }
    }
}