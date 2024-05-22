package com.example.shopmanagement.ui.admin.user

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.shopmanagement.ui.navigation.NavigationDestination

object UserScreenDestination : NavigationDestination {
    override val route: String = "user_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun UserAdminScreen() {
    Text(text = "User")
}