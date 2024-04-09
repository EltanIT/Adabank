package com.example.adabank.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.adabank.R


@Composable
fun SearchTextField(
    value: String,
    hilt: String,
    modifier: Modifier = Modifier,
    onValueChangeListener: (String) -> Unit,
    onSearchClickListener: () -> Unit,
) {

    BasicTextField(
        value = value,
        onValueChange = {onValueChangeListener(it)},
    modifier = modifier
        .height(52.dp)
        .fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodySmall.copy(
            fontWeight = FontWeight(400),
            color = Color.Black.copy(alpha = 0.5f),
            textAlign = TextAlign.Start
        ),
    decorationBox = {
        Row(
            Modifier
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(15.dp)),
        verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(12.dp))
            Image(painter = painterResource(id = R.drawable.ic_search), contentDescription =null)
            Spacer(modifier = Modifier.width(20.dp))
            Box(Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart) {
                if (value.isEmpty()){
                    Text(text = hilt,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight(400),
                        color = Color.Black.copy(alpha = 0.5f)
                    ))
                }
                it()
            }
        }
    })
}