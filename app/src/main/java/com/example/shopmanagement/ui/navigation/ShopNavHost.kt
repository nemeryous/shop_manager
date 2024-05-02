package com.example.shopmanagement.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shopmanagement.ui.admin.CategoryAddDestination
import com.example.shopmanagement.ui.admin.CategoryAddScreen
import com.example.shopmanagement.ui.admin.ProductAddDestination
import com.example.shopmanagement.ui.admin.ProductAddScreen
import com.example.shopmanagement.ui.home.HomeScreen
import com.example.shopmanagement.ui.home.HomeScreenDestination
import com.example.shopmanagement.ui.login.SignInDestination
import com.example.shopmanagement.ui.login.SignInScreen
import com.example.shopmanagement.ui.login.SignUpDestination
import com.example.shopmanagement.ui.login.SignUpScreen
import com.example.shopmanagement.ui.product.ProductDetailDestination
import com.example.shopmanagement.ui.product.ProductDetailScreen

@Composable
fun ShopNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
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
            HomeScreen(navigateToProductDetails = {
                navController.navigate("${ProductDetailDestination.route}/$it")
            },
                navigateToHome = { navController.navigate(HomeScreenDestination.route) },
                navigateToSetting = { navController.navigate(ProductDetailDestination.route) },
                navigateToSaveJob = { navController.navigate(ProductAddDestination.route) },
                navigateToProfile = {},
                navigateToPostJob = {}
            )
        }

        composable(route = ProductAddDestination.route) {
            ProductAddScreen()
        }

        composable(route = CategoryAddDestination.route) {
            CategoryAddScreen()
        }

        composable(
            route = ProductDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(ProductDetailDestination.productIdArg) {
                type = NavType.StringType
            })
        ) {
            ProductDetailScreen()
        }
    }
}