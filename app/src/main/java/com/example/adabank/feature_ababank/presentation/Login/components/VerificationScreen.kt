package com.example.adabank.feature_ababank.presentation.Login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adabank.core.presentation.components.PinCodeCompose
import com.example.adabank.ui.theme.Background2Color
import com.example.adabank.ui.theme.Poppins
import com.example.adabank.ui.theme.PrimaryColor


@ExperimentalMaterial3Api
@Composable
fun VerificationScreen(
    number: String,
    code: String,
    timer: String,
    onCodeChangeListener: (String) -> Unit,
) {

    val text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                fontFamily = FontFamily(Poppins),
                color = Color.Black
            )
        ){
            append("We have sent the code verification to\n" +
                    "your mobile number. ")
        }
        withStyle(
            SpanStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                fontFamily = FontFamily(Poppins),
                color = PrimaryColor
            )
        ){
            append("Wrong number ?")
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Background2Color, RoundedCornerShape(30.dp)),
        horizontalAlignment = Alignment.CenterHorizontally) {
        
        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Verification Code",
            style = MaterialTheme.typography.titleLarge.copy(
                lineHeight = 34.sp,
            ))
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = text,
            modifier = Modifier.alpha(0.5f),
            style = MaterialTheme.typography.titleLarge.copy(
                lineHeight = 18.sp,
                textAlign = TextAlign.Center
            ))
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier.background(PrimaryColor, CircleShape),
            contentAlignment = Alignment.Center){
            Text(text = number,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.White
                ),
                modifier = Modifier.padding(horizontal = 13.dp, vertical = 2.dp))
        }
        Spacer(modifier = Modifier.height(44.dp))
        PinCodeCompose(
            modifier = Modifier.padding(horizontal = 40.dp),
            value = code,
            onValueChangeListener = {onCodeChangeListener(it)})
        Spacer(modifier = Modifier.height(36.dp))
        Text(text = timer,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight(400)
            ),
            modifier = Modifier.alpha(0.5f))
        Spacer(modifier = Modifier.height(36.dp))

    }
    
}