package com.example.adabank.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun CustomProfileImage(
    image: Any,
    color: Color = Color.White,
    onClickListener: () -> Unit = {}
) {
    Box(
        Modifier
            .size(61.dp)
            .clickable { onClickListener() }
            .border(
                1.5.dp, Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = if (color == Color.White) 0.6f else  0.47f),
                        color.copy(alpha = 0.1f)
                    )
                ), CircleShape
            ),
        contentAlignment = Alignment.Center) {
        Image(
            painter = rememberAsyncImagePainter(model = image),
            contentDescription = null,
            Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.5f), CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}