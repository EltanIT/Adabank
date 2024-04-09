package com.example.adabank.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.adabank.ui.theme.PrimaryColor


@Composable
fun PinCodeCompose(
    modifier: Modifier,
    countItems: Int = 4,
    hiltText: String = "*",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    value: String,
    onValueChangeListener: (String) -> Unit
) {

    BasicTextField(
        value = value,
        onValueChange = {onValueChangeListener(it)},
        visualTransformation = visualTransformation,
    decorationBox = {
        Row(modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
            repeat(countItems){
                PinItem(
                    char = when {
                        it >= value.length -> hiltText
                        else -> value[it].toString()
                    }
                )
            }
        }
    })
}

@Composable
fun PinItem(
    char: String
) {
    Box(
        Modifier
            .size(59.dp)
            .background(PrimaryColor.copy(0.5f), RoundedCornerShape(15.dp)),
    contentAlignment = Alignment.Center) {
        Text(text = char,
        style = MaterialTheme.typography.titleMedium.copy(
            color = Color.White
        ))
    }
}