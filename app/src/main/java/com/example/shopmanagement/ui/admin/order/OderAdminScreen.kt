package com.example.shopmanagement.ui.admin.order

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.shopmanagement.ui.navigation.NavigationDestination

object OrderAdminScreenDestination : NavigationDestination {
    override val route: String = "order_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun OrderAdminScreen() {
Text(text = "Order")
}