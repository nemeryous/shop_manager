package com.example.shopmanagement.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ItemHomeAdmin(
    val title: String
)
data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

val itemHomeAdmin = listOf(
    ItemHomeAdmin("Add Product"),
    ItemHomeAdmin("All Product"),
    ItemHomeAdmin("Add Brand"),
    ItemHomeAdmin("All Brand"),
    ItemHomeAdmin("Add Category"),
    ItemHomeAdmin("All Category"),
    ItemHomeAdmin("Log Out"),
)
