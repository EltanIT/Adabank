package com.example.adabank.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adabank.R

@Composable
fun CustomInputNumber(
    onValueChangeListener: (String) -> Unit,
    onValueDeleteListener: () -> Unit,
    modifier: Modifier
) {

    Column(
        modifier
            .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "1",
                Modifier.clickable { onValueChangeListener("1") },
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 26.sp,
                lineHeight = 39.sp,
                fontWeight = FontWeight(500)
            ))
            Text(text = "2",
                Modifier.clickable { onValueChangeListener("2") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
            Text(text = "3",
                Modifier.clickable { onValueChangeListener("3") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "4",
                Modifier.clickable { onValueChangeListener("4") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
            Text(text = "5",
                Modifier.clickable { onValueChangeListener("5") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
            Text(text = "6",
                Modifier.clickable { onValueChangeListener("6") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "7",
                Modifier.clickable { onValueChangeListener("7") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
            Text(text = "8",
                Modifier.clickable { onValueChangeListener("8") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
            Text(text = "9",
                Modifier.clickable { onValueChangeListener("9") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
        }
        Spacer(modifier = Modifier.height(36.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = ".",
                Modifier.clickable { onValueChangeListener(",") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
            Text(text = "0",
                Modifier.clickable { onValueChangeListener("0") },
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    lineHeight = 39.sp,
                    fontWeight = FontWeight(500)
                ))
            Image(painter = painterResource(id = R.drawable.ic_arrow_left), contentDescription = null,
            modifier = Modifier.clickable { onValueDeleteListener() })
        }
    }
   

}