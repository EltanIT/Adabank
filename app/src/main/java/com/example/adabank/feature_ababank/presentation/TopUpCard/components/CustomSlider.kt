package com.example.adabank.feature_ababank.presentation.TopUpCard.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun CustomSlider(
    value: Float,
    maxValue: Float = 450.49f,
    onValueChangeListener: (Float) -> Unit,
    modifier: Modifier

) {

    Slider(
        value = value,
        onValueChange = {onValueChangeListener(it)},
        valueRange = 0f..maxValue,
        modifier = modifier.height(22.dp),
        colors = SliderDefaults.colors(
            activeTrackColor = PrimaryColor,
            inactiveTrackColor = PrimaryColor.copy(alpha = 0.5f),
            thumbColor = Color.White,
            inactiveTickColor = Color.White
        ),
    )
}