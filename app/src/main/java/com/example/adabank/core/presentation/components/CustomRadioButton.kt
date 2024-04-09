package com.example.adabank.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomRadioButton(
    isSelected: Boolean,
    modifier: Modifier
) {
    
    Box(
        contentAlignment = Alignment.Center
    ) {
        Box( modifier
            .size(32.dp)
            .clip(CircleShape)
            .border(1.dp, if (isSelected) Color.White else Color.Transparent, CircleShape))
        Box(
            Modifier
                .size(21.dp)
                .background(Color.White.copy(
                    if (isSelected) 1f else 0.5f
                ),
                    CircleShape)) {

        }
    }
}