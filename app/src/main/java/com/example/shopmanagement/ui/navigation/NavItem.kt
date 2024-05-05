package com.example.shopmanagement.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)
val listOfNavItems = listOf(
    NavItem(
        label = "HomeScreen",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = Screens.CartScreen.name
    ),
    NavItem(
        label = "Setting",
        icon = Icons.Default.Settings,
        route = Screens.SettingScreen.name
    )
)