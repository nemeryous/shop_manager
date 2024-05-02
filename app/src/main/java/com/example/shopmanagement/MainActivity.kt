package com.example.shopmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.shopmanagement.ui.admin.BrandAddScreen
import com.example.shopmanagement.ui.admin.ProductAddScreen
import com.example.shopmanagement.ui.home.HomeScreen
import com.example.shopmanagement.ui.navigation.ShopNavHost
import com.example.shopmanagement.ui.theme.ShopManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShopNavHost(navController = rememberNavController())

                }
                
            }
        }
    }
}
