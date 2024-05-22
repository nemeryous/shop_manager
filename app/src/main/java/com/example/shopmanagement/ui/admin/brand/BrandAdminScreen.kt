package com.example.shopmanagement.ui.admin.brand

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.shopmanagement.ui.navigation.NavigationDestination

object BrandAdminScreenDestination : NavigationDestination {
    override val route: String = "brand_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun BrandAdminScreen() {
Text(text = "Brand")
}