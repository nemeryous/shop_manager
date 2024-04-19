package com.example.shopmanagement.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shopmanagement.ui.home.HomeScreen
import com.example.shopmanagement.ui.home.HomeScreenDestination
import com.example.shopmanagement.ui.login.SignInDestination
import com.example.shopmanagement.ui.login.SignInScreen
import com.example.shopmanagement.ui.login.SignUpDestination
import com.example.shopmanagement.ui.login.SignUpScreen
import com.example.shopmanagement.ui.product.ProductAddDestination
import com.example.shopmanagement.ui.product.ProductAddScreen

@Composable
fun ShopNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SignInDestination.route,
        modifier = modifier
    ) {
        composable(route = SignInDestination.route) {
            SignInScreen(
                navigateToSignUp = {
                    navController.navigate(SignUpDestination.route)
                },
                navigateToHome = {
                    navController.navigate(HomeScreenDestination.route)
                }
            )
        }

        composable(route = SignUpDestination.route) {
            SignUpScreen(navigateToSignIn = {
                navController.navigate(SignInDestination.route)
            })
        }
        composable(route = HomeScreenDestination.route) {
            HomeScreen(navigationToProductAdd = {
                navController.navigate(ProductAddDestination.route)
            })
        }

        composable(route = ProductAddDestination.route) {
            ProductAddScreen()
        }
    }
}