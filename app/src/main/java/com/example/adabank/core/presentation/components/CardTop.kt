package com.example.adabank.core.presentation.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.adabank.R
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.BackgroundColor


@Composable
fun CardTop(
    title: String,
    balance: String,
    card: String,
    modifier: Modifier
) {

    Box(modifier = modifier.fillMaxWidth()){
        Image(painter = painterResource(id = R.drawable.ic_card_ellipse),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Box {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(85.dp)
                    .background(
                        Background2Color,
                        RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
                    )
                    .align(Alignment.BottomCenter)
            )
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text(text = title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = Color.White
                    ))
                Spacer(modifier = Modifier.height(36.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(Color.Black, RoundedCornerShape(30.dp))
                        .clip(RoundedCornerShape(30.dp))) {
                    Box(modifier = Modifier.weight(1f)){
                        Image(
                            painter = painterResource(id = R.drawable.ic_card_ellipse_2),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.BottomStart)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_card_ellipse_3),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.TopEnd)
                        )
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp, start = 31.dp, bottom = 17.dp)) {
                            Text(text = "Current Balance",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Background2Color.copy(alpha = 0.54f),
                                    fontWeight = FontWeight(500)
                                ))
                            Spacer(modifier = Modifier.height(1.dp))
                            Text(text = "$$balance",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    color = Background2Color,
                                    fontWeight = FontWeight(500),
                                    fontSize = 28.sp,
                                    lineHeight = 42.sp
                                ))
                            Spacer(modifier = Modifier.height(52.dp))
                            Text(text = card,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Background2Color.copy(alpha = 0.9f),
                                    fontWeight = FontWeight(500),
                                ))
                        }
                    }
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(Color(0xffCCEF69))) {
                        Image(painter = painterResource(id = R.drawable.ic_ornament_card),
                            contentDescription = null
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top=24.dp, bottom = 17.dp, start=5.dp)) {
                            Image(painter = painterResource(id = R.drawable.ic_mastercard_logo), contentDescription = null)
                            Spacer(modifier = Modifier.height(85.dp))
                            Text(text = "09/25",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.Black,
                                    fontWeight = FontWeight(500),
                                ))
                        }
                    }

                }
            }
        }
        

    }

}