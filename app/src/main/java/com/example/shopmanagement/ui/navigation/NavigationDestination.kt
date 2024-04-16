package com.example.shopmanagement.ui.navigation

interface NavigationDestination {
// Define path for the composable
    val route: String

//    String resource Id contain title to be displayed for the screen
    val titleRes: Int

}