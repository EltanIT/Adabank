package com.example.adabank.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adabank.feature_ababank.domain.model.TransactionData


@Composable
fun TransactionCategoryItem(
    transactionData: TransactionData,
    modifier: Modifier = Modifier
) {
    
    Row(
        modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)) {
            Box(Modifier
                .blur(30.dp)
                .background(Color.White.copy(0.5f), RoundedCornerShape(12.dp))) {
                Image(
                    painter = painterResource(id = transactionData.image),
                    contentDescription = null,
                    Modifier.padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Column() {
                Text(text = transactionData.name,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight(500)
                ))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = transactionData.created_at,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge.copy(
                        lineHeight = 15.sp,
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.5f)
                    ))
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = "$"+transactionData.total,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight(500)
                ))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = transactionData.description,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge.copy(
                    lineHeight = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color.Black.copy(alpha = 0.5f)
                ))
        }
       
    }


}