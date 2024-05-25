package com.example.shopmanagement.ui.login

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Hello() {
    Text(text = "Hello World", style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.Serif))
//    Text(text = "Hello World", style = MaterialTheme.typography.titleMedium)
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HelloPreview() {
    Hello()
}