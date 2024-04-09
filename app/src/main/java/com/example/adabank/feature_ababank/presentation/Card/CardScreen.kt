package com.example.adabank.feature_ababank.presentation.Card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CardTop
import com.example.adabank.core.presentation.components.CustomSwitch
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color


@Composable
fun CardScreen(
    navController: NavController,
    viewModel: CardViewModel = hiltViewModel()
) {

    val state = viewModel.state.value


    Column(Modifier.fillMaxSize()) {
        CardTop(title = "My Card",
            balance = state.balance,
            card = state.card,
            modifier = Modifier)
        Column(
            Modifier
                .fillMaxSize()
                .background(Background2Color)) {
            Spacer(modifier = Modifier.height(44.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 23.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                repeat(viewModel.actionList.size){
                    if (it==2){
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                    Box(
                        Modifier
                            .background(Color.White, RoundedCornerShape(30.dp))
                            .clip(RoundedCornerShape(30.dp))
                            .weight(1f)
                            .clickable {
                                when (it) {
                                    0 -> {}
                                    1 -> {
                                        navController.navigate(
                                            Route.ChangePin.route.replace(
                                                "{balance}",
                                                state.balance
                                            )
                                        )
                                    }

                                    2 -> {
                                        navController.navigate(
                                            Route.TopUpCard.route.replace(
                                                "{balance}",
                                                state.balance
                                            )
                                        )
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 9.dp)
                        ) {
                            Image(
                                painter = painterResource(id = viewModel.actionList[it].icon),
                                contentDescription = null)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = viewModel.actionList[it].title,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight(400)
                            ))
                        }
                    }
                    if (it==0){
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(44.dp))
            Text(text = "Settings",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight(500),
                    color = Color.Black.copy(alpha = 0.5f)
                ),
            modifier = Modifier.padding(horizontal = 24.dp))
            Spacer(modifier = Modifier.height(17.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(text = "Set card limit",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(500),
                            color = Color.Black
                        ))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "You set budget for 3 categories",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight(400),
                            lineHeight = 15.sp,
                            color = Color.Black.copy(alpha = 0.5f)
                        ))
                }
                CustomSwitch(isCheck = state.isCardLimitEnable) {
                    viewModel.onEvent(CardEvent.isCardLimitEnable)
                }
            }
            Spacer(modifier = Modifier.height(23.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 24.dp)
                .background(Color.Black.copy(alpha = 0.1f)))
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text(text = "Set card limit",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(500),
                            color = Color.Black
                        ))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "You set limit per transaction",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight(400),
                            lineHeight = 15.sp,
                            color = Color.Black.copy(alpha = 0.5f)
                        ))
                }
                Text(text = "$100.00",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight(500),
                        color = Color.Black
                    ))
            }


            Spacer(modifier = Modifier.height(23.dp))
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 24.dp)
                .background(Color.Black.copy(alpha = 0.1f)))

        }
    }

}