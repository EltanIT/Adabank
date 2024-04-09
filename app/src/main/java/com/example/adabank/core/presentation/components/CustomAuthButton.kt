package com.example.adabank.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.adabank.ui.theme.PrimaryColor

@Composable
fun CustomAuthButton(
    text: String,
    state: Boolean,
    isDimly: Boolean = false,
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit
) {

    Button(onClick = {
        if (state){
            onClickListener()
        }
    },
        modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor =  PrimaryColor.copy(
                alpha = if (isDimly) 0.5f else 1f
            )
        )

    ) {
        Text(text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight(500),
                color = Color.White.copy(
                    alpha = if (isDimly) 0.5f else 1f
                )
            ))
    }

}