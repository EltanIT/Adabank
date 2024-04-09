package com.example.adabank.feature_ababank.presentation.Notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.adabank.ui.theme.Background2Color


@Composable
fun NotificationScreen(

) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Background2Color)){
        Text(text = "Notification",
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight(500)
        ),
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 9.dp)

        )
    }
}