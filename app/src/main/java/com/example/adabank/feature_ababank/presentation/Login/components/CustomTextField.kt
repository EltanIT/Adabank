package com.example.adabank.feature_ababank.presentation.Login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.adabank.R
import com.example.adabank.ui.theme.PrimaryColor

@Composable
fun CustomTextField(
    value: String,
    preNumbers: List<String>,
    selectedPreNumber: String
) {

    Row(Modifier.fillMaxWidth()) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = selectedPreNumber,
                    style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.alpha(0.2f))
                Spacer(modifier = Modifier.width(12.dp))
                Icon(painter = painterResource(id = R.drawable.ic_arrow_down), contentDescription = null,
                modifier = Modifier.alpha(0.2f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier
                .height(1.5.dp)
                .width(70.dp)
                .alpha(0.2f)
                .background(Color.Black))
            
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(Modifier.fillMaxWidth()) {
            Text(text = value,
            style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier
                .height(1.5.dp)
                .fillMaxWidth()
                .background(PrimaryColor))
        }

    }
}