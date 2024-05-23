package com.example.shopmanagement.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shopmanagement.R


val AbrilFatface = FontFamily(
    Font(R.font.dm_sans)
)

val Montserrat = FontFamily(
    Font(R.font.dm_sans),
    Font(R.font.dm_sans_bold, FontWeight.Bold)
)

val Alike = FontFamily(
    Font(R.font.alike),
    Font(R.font.alike_angular, FontWeight.Bold)
)

val typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Alike,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),

    titleMedium =  TextStyle(
        fontFamily = Alike,
        fontWeight = FontWeight.Bold,
        fontSize = 54.sp
    ),
)