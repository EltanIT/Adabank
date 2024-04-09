package com.example.adabank.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
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
fun CustomAlertDialog(
    exception: String,
    onDismissListener: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismissListener,
        confirmButton = {
            Button(
                onClick = onDismissListener,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor
                ),
                shape = CircleShape,
                elevation = ButtonDefaults.buttonElevation(
                    0.dp
                ),
                modifier = Modifier.fillMaxWidth().height(64.dp)
                ) {
                Text(text = "Закрыть",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight(500),
                        color = Color.White
                    )
                )
            }
        },
        title = {
            Text(text = "Ошибка!",
                style = MaterialTheme.typography.bodyMedium
                )
        },
        text = {
            Text(text = exception,
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}