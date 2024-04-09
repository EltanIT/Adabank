package com.example.adabank.feature_ababank.presentation.GraphDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun GraphDetailsScreen(
    navController: NavController,
    viewModel: GraphDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopAppBar(title = "") {
            navController.popBackStack()
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Equipment",
            style = MaterialTheme.typography.titleSmall.copy(
                color = Color.White,
                fontSize = 36.sp,
                lineHeight = 54.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "$450,49",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White,
                fontWeight = FontWeight(400)
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
        Spacer(modifier = Modifier.height(17.dp))
        Column(
            Modifier
                .fillMaxSize()
                .background(
                    Background2Color,
                    RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                )) {
            Spacer(modifier = Modifier.height(20.dp))
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
                itemsIndexed(state.transactionsList){index, item ->
                    TransactionItem(
                        title = item.name,
                        date = item.created_at,
                        total = item.total,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )

                    if (index == state.transactionsList.size-1){
                        Spacer(modifier = Modifier.height(16.dp))
                    }else{
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

        }


    }

}

@Composable
fun TransactionItem(
    title: String,
    date: String,
    total: String,
    modifier: Modifier
) {
    Row(
        modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)) {
            Box(Modifier
                .background(Color.White.copy(0.5f), RoundedCornerShape(12.dp))) {
                Text(
                    text = "TA",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight(500),
                        color = PrimaryColor
                    ),
                    modifier = Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column() {
                Text(text = title,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight(500)
                    ))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = date,
                    style = MaterialTheme.typography.labelLarge.copy(
                        lineHeight = 15.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.5f)
                    ))
            }

        }

        Text(text = "$$total",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight(500)
            ))
    }

}