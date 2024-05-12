package com.example.shopmanagement.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopmanagement.ui.admin.home.HomeAdminScreen
import com.example.shopmanagement.ui.admin.home.HomeAdminScreenDestination
import com.example.shopmanagement.ui.cart.ShoppingCartScreen
import com.example.shopmanagement.ui.home.HomeScreen
import com.example.shopmanagement.ui.home.SettingScreen
import com.example.shopmanagement.ui.login.SignInDestination
import com.example.shopmanagement.ui.login.SignInScreen
import com.example.shopmanagement.ui.login.SignUpDestination
import com.example.shopmanagement.ui.login.SignUpScreen
import com.example.shopmanagement.ui.product.ProductDetailDestination
import com.example.shopmanagement.ui.product.ProductDetailScreen


@Composable
fun ShopNavHost(
//    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected =currentDestination?.hierarchy?.any{ it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = SignInDestination.route) {
                SignInScreen(
                    navigateToSignUp = {
                        navController.navigate(SignUpDestination.route)
                    },
                    navigateToHome = {
                        navController.navigate(Screens.HomeScreen.name)
                    },
                    navigateToAdmin = {
                        navController.navigate(HomeAdminScreenDestination.route)
                    }
                )
            }

            composable(route = SignUpDestination.route) {
                SignUpScreen(navigateToSignIn = {
                    navController.navigate(SignInDestination.route)
                })
            }
            composable(route = Screens.HomeScreen.name) {
                HomeScreen(navigateToProductDetails = {
                    navController.navigate("${ProductDetailDestination.route}/$it")
                })
            }
            composable(route = Screens.CartScreen.name) {
                ShoppingCartScreen()
            }
            composable(route = Screens.SettingScreen.name) {
                SettingScreen()
            }
            composable(
                route = ProductDetailDestination.routeWithArgs,
                arguments = listOf(navArgument(ProductDetailDestination.productIdArg) {
                    type = NavType.StringType
                })
            ) {
                ProductDetailScreen()
            }

            composable(
                route = HomeAdminScreenDestination.route
            ) {
                HomeAdminScreen()
            }
        }

    }
}

//object AuthNavHostDestination: NavigationDestination {
//    override val route: String = "auth_nav_host"
//    override val titleRes: Int
//        get() = TODO("Not yet implemented")
//
//}
//@Composable
//fun AuthNavHost(navController: NavHostController) {
//    NavHost(navController = navController, startDestination = SignInDestination.route) {
//        composable(route = SignInDestination.route) {
//            SignInScreen(
//                navigateToSignUp = {
//                    navController.navigate(SignUpDestination.route)
//                },
//                navigateToHome = {
//                    navController.navigate(Screens.HomeScreen.name)
//                },
//                navigateToAdmin = {
//                    navController.navigate(HomeAdminScreenDestination.route)
//                }
//            )
//        }
//        composable(route = SignUpDestination.route) {
//            SignUpScreen(navigateToSignIn = {
//                navController.navigate(SignInDestination.route)
//            })
//        }
//        composable(
//            route = HomeAdminScreenDestination.route
//        ) {
//            HomeAdminScreen()
//        }
//    }
//}

//@Composable
//fun ShopManagementAppNavHost(navController: NavHostController) {
//
//    NavHost(navController = navController, startDestination = AuthNavHostDestination.route) {
//        composable(route = AuthNavHostDestination.route) {
//            AuthNavHost(navController = navController)
//        }
//
//    }
//}