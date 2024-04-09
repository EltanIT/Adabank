package com.example.adabank.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.adabank.R


@Composable
fun CustomTopAppBar(
    title: String,
    color: Color = Color.White,
    modifier: Modifier = Modifier,
    onBackClickListener: () -> Unit
) {

    var isEnable by remember{
        mutableStateOf(true)
    }

    Box(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 4.dp)) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterStart),
            enabled = isEnable,
            onClick = {
                isEnable = !isEnable
                onBackClickListener()
            }) {
            Icon(painter = painterResource(id = R.drawable.ic_back),
                tint = color,
                contentDescription = null
            )
        }

        Text(text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = color,
                fontWeight = FontWeight(500)
            ),
            modifier = Modifier.align(Alignment.Center))
    }
}