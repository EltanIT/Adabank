package com.example.adabank.feature_ababank.presentation.MenuScreen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.adabank.R


@Composable
fun MenuItemCompose(
    title: String,
    @DrawableRes icon: Int,
    modifier: Modifier
) {
    Row(modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = icon), contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title,
            modifier = Modifier.weight(1f),
        style = MaterialTheme.typography.bodyMedium.copy(
            color = Color.White,
            fontWeight = FontWeight(400),
            textAlign = TextAlign.Start
        )
        )
        Image(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = null)
    }
}