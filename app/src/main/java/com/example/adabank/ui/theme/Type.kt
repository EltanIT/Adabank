package com.example.adabank.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.adabank.R


val Poppins = listOf(
    Font(R.font.poppins_light, FontWeight.W100),
    Font(R.font.poppins_light, FontWeight.W200),
    Font(R.font.poppins_light, FontWeight.W300),
    Font(R.font.poppins_regular, FontWeight.W400),
    Font(R.font.poppins_medium, FontWeight.W500),
    Font(R.font.poppins_bold, FontWeight.W600),
    Font(R.font.poppins_bold, FontWeight.W700),
    Font(R.font.poppins_black, FontWeight.W800),
    Font(R.font.poppins_black, FontWeight.W900),
)


// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(Poppins),
        fontWeight = FontWeight(600),
        fontSize = 24.sp,
        lineHeight = 36.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Poppins),
        fontWeight = FontWeight(400),
        fontSize = 23.sp,
        lineHeight = 34.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Poppins),
        fontWeight = FontWeight(500),
        fontSize = 20.sp,
        lineHeight = 30.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    ),



    bodyLarge = TextStyle(
        fontFamily = FontFamily(Poppins),
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    ),

    bodyMedium = TextStyle(
        fontFamily = FontFamily(Poppins),
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        lineHeight = 21.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    ),

    bodySmall = TextStyle(
        fontFamily = FontFamily(Poppins),
        fontWeight = FontWeight(600),
        fontSize = 12.sp,
        lineHeight = 18.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    ),

    labelLarge = TextStyle(
        fontFamily = FontFamily(Poppins),
        fontWeight = FontWeight(500),
        fontSize = 10.sp,
        lineHeight = 10.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    ),


)