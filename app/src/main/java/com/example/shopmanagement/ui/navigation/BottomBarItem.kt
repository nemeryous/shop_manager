package com.example.shopmanagement.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarItem(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarItem(
        route = "PROFILE",
        title = "PROFILE",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarItem(
        route = "SETTINGS",
        title = "SETTINGS",
        icon = Icons.Default.Settings
    )
}