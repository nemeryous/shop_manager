package com.example.shopmanagement

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.shopmanagement.ui.login.SignUpScreen
import com.example.shopmanagement.ui.navigation.ShopNavHost

@Composable
fun ShopManagementApp() {
    ShopNavHost(navController = rememberNavController())
}