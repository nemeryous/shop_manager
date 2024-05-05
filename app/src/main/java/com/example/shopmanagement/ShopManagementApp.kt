package com.example.shopmanagement

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.shopmanagement.ui.navigation.ShopNavHost
import com.example.shopmanagement.ui.theme.ShopManagementTheme

@Composable
fun ShopManagementApp() {
    val navController = rememberNavController()
    ShopManagementTheme {

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerpadding ->
            ShopNavHost(
//                navController = navController,
                modifier = Modifier.padding(innerpadding)
            )
        }


    }

}
