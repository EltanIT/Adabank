package com.example.adabank.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun CustomContactItem(
    name: String,
    bank: String,
    image: Any,
    modifier: Modifier,
    color: Color,
    fontSize: TextUnit = 16.sp,
    lineHeight: TextUnit = 16.sp,
) {

    Row(modifier
        .clip(RoundedCornerShape(15.dp))
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberAsyncImagePainter(model = image),
            contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(color.copy(alpha = 0.4f))
        )
        Spacer(modifier = Modifier.width(24.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight(500),
                    lineHeight = lineHeight,
                    fontSize = fontSize,
                    color = color,
                    textAlign = TextAlign.Start
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Bank - $bank",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight(400),
                    lineHeight = 12.sp,
                    color = color.copy(alpha = 0.5f)

                )
            )
        }
    }
}