package com.example.adabank.feature_ababank.presentation.ChangePin.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.adabank.R


@Composable
fun CustomTextField(
    value: String,
    hilt: String,
    isVisible: Boolean,
    onValueChangeListener: (String) -> Unit,
    onValueVisibleListener: () -> Unit,
) {

    BasicTextField(
        value = value,
        onValueChange = {onValueChangeListener(it)},
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight(400),
            textAlign = TextAlign.Start
        ),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation('*'),
        modifier = Modifier.fillMaxWidth(),
        decorationBox = {
            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.weight(1f)){
                        if (value.isEmpty()){
                            Text(text = hilt,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight(400),
                                color = Color.Black.copy(alpha = 0.5f)
                            ),)
                        }
                        it()
                    }
                    Icon(painter = painterResource(id = R.drawable.ic_eye_show),
                        contentDescription = null,
                        tint = Color.Black.copy(alpha = 0.5f),
                        modifier = Modifier.clickable { onValueVisibleListener() }
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Black.copy(alpha = 0.1f))
                )
            }
        }
    )
}