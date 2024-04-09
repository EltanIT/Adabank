package com.example.adabank.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun CustomSwitch(
    isCheck: Boolean,
    color: Color = PrimaryColor,
    onCheckClickListener: () -> Unit
) {
    
    Box(Modifier
            .width(50.dp)
            .clip(CircleShape)
            .background(if (isCheck) PrimaryColor else color.copy(alpha = 0.2f), CircleShape)
            .clickable { onCheckClickListener() },
        contentAlignment = if (isCheck) Alignment.CenterEnd else Alignment.CenterStart
        ){
        Spacer(modifier = Modifier.size(26.dp).padding(2.dp).background(Color.White, CircleShape))
    }
}