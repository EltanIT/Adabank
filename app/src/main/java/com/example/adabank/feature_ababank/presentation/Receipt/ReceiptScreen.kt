package com.example.adabank.feature_ababank.presentation.Receipt

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adabank.R
import com.example.adabank.core.presentation.components.CustomAuthButton
import com.example.adabank.core.presentation.components.CustomContactItem
import com.example.adabank.core.presentation.components.CustomTopAppBar
import com.example.adabank.ui.theme.Background2Color


@Composable
fun ReceiptScreen(
    navController: NavController,
    viewModel: ReceiptViewModel
) {

    val state = viewModel.state.value

    Column(
        Modifier
            .fillMaxSize()
            .background(Background2Color)) {
        CustomTopAppBar(
            title = "",
        color = Color(0xff080422)) {
            navController.popBackStack()
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(22.dp))
            Text(text = "My Receipt",
            style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(24.dp))

            Card(modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    1.dp
                ),
                shape = RoundedCornerShape(30.dp)) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 26.dp, end = 26.dp, top = 32.dp, bottom = 49.dp),
                     horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    CustomContactItem(
                        name = state.contactData.name,
                        bank = state.contactData.bank,
                        image = state.contactData.image?:"",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Black,
                        fontSize = 20.sp,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Black.copy(alpha = 0.1f))
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row(Modifier.fillMaxWidth()) {
                        Text(text = "Total",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Black.copy(alpha = 0.5f),
                                textAlign = TextAlign.Start
                            ),
                            modifier = Modifier.weight(1f))
                        Text(text = "$"+state.receiptData.total,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Black.copy(alpha = 0.5f)
                            ))
                    }

                    Spacer(modifier = Modifier.height(26.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Black.copy(alpha = 0.1f))
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = "Note",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black.copy(alpha = 0.5f),
                            textAlign = TextAlign.Start
                        ),
                    modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(26.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Black.copy(alpha = 0.1f))
                    )
                    Spacer(modifier = Modifier.height(20.dp))


                    Row(Modifier.fillMaxWidth()) {
                        Text(text = "Date Transaction",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black.copy(alpha = 0.5f),
                                fontWeight = FontWeight(400),
                                textAlign = TextAlign.Start
                            ),
                            modifier = Modifier.weight(1f))
                        Text(text = state.receiptData.created_at,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Black.copy(alpha = 0.5f)
                            ))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
                        Text(text = "ID Transaction",
                            maxLines = 1,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Black.copy(alpha = 0.5f),
                                fontWeight = FontWeight(400),
                                textAlign = TextAlign.Start
                            ),
                            modifier = Modifier.weight(1f))
                        Text(text = "202108260001@\n" +
                                "DCB199983",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Color.Black.copy(alpha = 0.5f),
                                textAlign = TextAlign.End
                            ))
                    }

                    Spacer(modifier = Modifier.height(11.dp))
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Black.copy(alpha = 0.1f))
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Card(modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    1.dp
                ),
                shape = RoundedCornerShape(30.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 26.dp, end = 26.dp, top = 18.dp, bottom = 17.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Categories",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black.copy(alpha = 0.5f),
                            textAlign = TextAlign.Start
                        )
                    )

                    Row(verticalAlignment = Alignment.CenterVertically){
                        Box(
                            Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    Color.White.copy(alpha = 0.5f),
                                    RoundedCornerShape(12.dp))
                                .shadow(0.5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(painter = painterResource(id = R.drawable.ic_category_equipment), contentDescription = null)
                        }

                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = state.receiptData.category,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight(500)
                            ))
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = null)
                    }
                }}

            Spacer(modifier = Modifier.weight(1f))
            CustomAuthButton(
                text = "DOWNLOAD INVOICE",
                state = true) {
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}