package com.example.shopmanagement.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopmanagement.R
import com.example.shopmanagement.ui.components.BoldTextComponent
import com.example.shopmanagement.ui.navigation.NavigationDestination


object HomeScreenDestination : NavigationDestination {
    override val route: String = "home_screen"

    override val titleRes: Int = R.string.home_screen

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
        ) {

            BoldTextComponent(value = "Hello")

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}