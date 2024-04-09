package com.example.adabank.feature_ababank.presentation.TopUpCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.core.presentation.components.CardTop
import com.example.adabank.feature_ababank.presentation.TopUpCard.components.CustomSlider
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor
import com.example.adabank.ui.theme.PrimaryColor


@ExperimentalLayoutApi
@Composable
fun TopUpCardScreen(
    navController: NavController,
    viewModel: TopUpCardViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)) {
        CardTop(
            title = "Top Up",
            balance = state.balance,
            card = state.card,
            modifier = Modifier
        )
        Column(
            Modifier
                .fillMaxSize()
                .background(Background2Color)) {
            Spacer(modifier = Modifier.height(66.dp))
            Text(text = "Total Top Up",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Black.copy(alpha = 0.5f),
                    fontWeight = FontWeight(400)),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$${state.total}",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight(400)
                ),
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black.copy(alpha = 0.1f))
            )


            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Saldo Active",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Black.copy(alpha = 0.5f),
                        fontWeight = FontWeight(400))
                )
                Text(text = "$${state.saldoActive}",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Black.copy(alpha = 0.5f),
                        fontWeight = FontWeight(400))
                )
            }


            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly) {
                CustomSlider(
                    value = state.totalFloat,
                    onValueChangeListener = {viewModel.onEvent(TopUpCardEvent.SliderMove(it))},
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                FlowRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    repeat(viewModel.listAmounts.size){index ->
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .padding(bottom = 12.dp)
                            .background(
                                if (viewModel.listAmounts[index].equals(state.availableTotal)) PrimaryColor else Color.Black.copy(
                                    0.2f
                                ),
                                RoundedCornerShape(10.dp)
                            )){
                            Text(text = viewModel.listAmounts[index],
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color.White.copy(if (viewModel.listAmounts[index].equals(state.availableTotal)) 1f else 0.5f)
                                ),
                                modifier = Modifier.padding(horizontal = 17.dp, vertical = 9.dp)
                            )
                        }
                    }

                }

                Text(
                    text = "Money will be moved from \n" +
                            "active balance to add card balance",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Black.copy(0.5f),
                        fontWeight = FontWeight(400)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

            }


        }
    }
}