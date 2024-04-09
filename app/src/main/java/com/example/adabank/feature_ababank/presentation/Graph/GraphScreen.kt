package com.example.adabank.feature_ababank.presentation.Graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.feature_ababank.presentation.Graph.components.TransactionCategoryItem
import com.example.adabank.feature_ababank.presentation.GraphDetails.TransactionItem
import com.example.adabank.feature_ababank.presentation.navgraph.Route
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun GraphScreen(
    navController: NavController,
    viewModel: GraphViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.rotate(90f)
            )
            Text(text = "1 Sep 2021 - 30 Sep 2021",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.White.copy(0.5f)
                ),
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.rotate(-90f)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "$450,49",
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.White,
                fontSize = 36.sp,
                lineHeight = 54.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_view_graphic),
            contentDescription = null,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(28.dp))
        Box(Modifier.fillMaxSize()) {

            Column(
                Modifier
                    .fillMaxSize()
                    .background(
                        Background2Color,
                        RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                    )
            ) {
                Spacer(modifier = Modifier.height(101.dp))
                Text(
                    text = "Transaction History",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight(500),
                        color = Color.Black.copy(0.5f)
                    ),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                LazyColumn(){
                    itemsIndexed(state.transactionsHistoryCategory){index, item ->
                        TransactionCategoryItem(
                            title = item.name,
                            description = item.description,
                            total = item.total,
                            image = item.image,
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .clickable { navController.navigate(Route.GraphDetails.route) }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        if (index == state.transactionsHistoryCategory.size-1){
//                            Spacer(modifier = Modifier.height(66.dp))
                        }
                    }
                }

            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    2.dp
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y= (-22).dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 36.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(text = "Earned",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black.copy(0.5f),
                                fontWeight = FontWeight(400)
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "$450,49",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = PrimaryColor
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(34.dp))

                    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    Canvas(
                        Modifier
                            .height(59.dp)
                            .width(1.dp)
                            .rotate(-90f)
                    ) {

                        drawLine(
                            color = Color.Black.copy(0.5f),
                            start = Offset(0f, 0f),
                            end = Offset(size.height, 0f),
                            pathEffect = pathEffect
                        )
                    }
                    Spacer(modifier = Modifier.width(34.dp))

                    Column() {
                        Text(text = "Spent",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black.copy(0.5f),
                                fontWeight = FontWeight(400)
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "230,12",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = Color(0xffFF7272)
                            )
                        )
                    }
                }
            }
        }

        
    }
}