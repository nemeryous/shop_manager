package com.example.shopmanagement.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shopmanagement.R


<<<<<<< HEAD
val AbrilFatface = FontFamily(
    Font(R.font.dm_sans)
)

val Montserrat = FontFamily(
=======
// Set of Material typography styles to start with

val DmSans = FontFamily(
>>>>>>> b379c96851f72c15affd3a85d5e950a0f9abdce4
    Font(R.font.dm_sans),
    Font(R.font.dm_sans_bold, FontWeight.Bold)
)

<<<<<<< HEAD
val Alike = FontFamily(
    Font(R.font.alike),
    Font(R.font.alike_angular, FontWeight.Bold)
)

val typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
=======
val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_bold, FontWeight.Bold)

)
val AbrilFatface = FontFamily(
    Font(R.font.abril_fatface_regular)
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
>>>>>>> b379c96851f72c15affd3a85d5e950a0f9abdce4
    ),
    displayMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    displaySmall = TextStyle(
        fontFamily = AbrilFatface,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    labelSmall = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight(600),
        fontSize = 14.sp
    ),
    labelMedium =TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ) ,
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
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
<<<<<<< HEAD
=======
    titleLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )



>>>>>>> b379c96851f72c15affd3a85d5e950a0f9abdce4
)