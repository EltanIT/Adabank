package com.example.adabank.feature_ababank.presentation.TransferDetail

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomContactItem
import com.example.adabank.core.presentation.components.CustomInputNumber
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun TransferDetailScreen(
    navController: NavController,
    viewModel: TransferDetailViewModel
) {

    val state = viewModel.state.value


    LaunchedEffect(!state.stateSwap){
        if (state.stateSwap){
            navController.popBackStack()
            navController.navigate(Route.Receipt.route
                .replace("{receipt}", viewModel.getData())
                .replace("{contact}", viewModel.getContact())
            )
        }
    }



    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)) {
        CustomTopAppBar(title = "Transfer") {
            navController.popBackStack()
        }
        Column(Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(20.dp))
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.15f)
                ),
                elevation = CardDefaults.cardElevation(
                    0.dp
                ),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(2.dp, Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.5f),
                        Color.White.copy(alpha = 0.05f)
                    )
                ))
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Enter Amount",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight(400),
                            color = Color.White.copy(alpha = 0.6f)
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "$"+state.amount,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 44.sp,
                            lineHeight = 66.sp,
                            color = Color.White
                        ))
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(10.dp))){
                        Row(Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                            Text(text = "USD",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    color = Color.White
                                ))
                            Image(painter = painterResource(id = R.drawable.ic_arrow_down_2), contentDescription = null)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.3f)))
                    Spacer(modifier = Modifier.height(20.dp))


                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .border(1.dp, Color.White, RoundedCornerShape(15.dp)),
                    contentAlignment = Alignment.Center) {
                       CustomContactItem(
                           name = state.contact.name,
                           bank = state.contact.bank,
                           image = state.contact.image?:"",
                           modifier = Modifier.padding(horizontal = 12.dp, vertical = 15.dp),
                       color = Color.White)
                    }
                }
            }
            Spacer(modifier = Modifier.height(28.dp))

            Column(
                Modifier
                    .weight(1f)
                    .background(
                        Background2Color,
                        RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )) {
                Spacer(modifier = Modifier.height(21.dp))
                CustomInputNumber(
                    onValueChangeListener = {viewModel.onEvent(TransferDetailEvent.EnteredAmount(it))},
                    onValueDeleteListener = {viewModel.onEvent(TransferDetailEvent.DeleteAmount)},
                    modifier = Modifier.padding(horizontal = 40.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                val localDensity = LocalDensity.current

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp)
                        .background(PrimaryColor, CircleShape)
                        .onGloballyPositioned { coordinates ->
                            viewModel.setWidthSwipeBox(
                                coordinates.size.width.toFloat(),
                                localDensity
                            )
                        }
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures { change, dragAmount ->
                                Log.i("pay", dragAmount.toString() + "  " + change.position)
                                viewModel.onEvent(TransferDetailEvent.EnteredSwipeCoordinate(change.position.x))
                            }
                        }

                ){
                    Text(text = "SWIPE TO PAY",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight(500),
                            color = Color.White),
                        modifier = Modifier.align(Alignment.Center)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ic_swipe),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 2.dp, vertical = 2.dp)
                            .offset(x = state.coordinate)
                            .align(Alignment.CenterStart)
                            .onGloballyPositioned { coordinates ->
                                viewModel.setWidthSwipe(coordinates.size.width.toFloat())
                            }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}