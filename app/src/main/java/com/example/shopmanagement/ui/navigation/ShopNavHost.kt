package com.example.shopmanagement.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.shopmanagement.ui.admin.BrandAddDestination
import com.example.shopmanagement.ui.admin.BrandAddScreen
import com.example.shopmanagement.ui.admin.CategoryAddDestination
import com.example.shopmanagement.ui.admin.CategoryAddScreen
import com.example.shopmanagement.ui.admin.ProductAddDestination
import com.example.shopmanagement.ui.admin.ProductAddScreen
import com.example.shopmanagement.ui.admin.home.HomeAdminScreen
import com.example.shopmanagement.ui.admin.home.HomeAdminScreenDestination
import com.example.shopmanagement.ui.cart.ShoppingCartScreen
import com.example.shopmanagement.ui.checkout.AddNewAddressPage
import com.example.shopmanagement.ui.checkout.AddNewAddressScreenDestination
import com.example.shopmanagement.ui.checkout.AddressScreen
import com.example.shopmanagement.ui.checkout.AddressScreenDestination
import com.example.shopmanagement.ui.checkout.CheckOutDestination
import com.example.shopmanagement.ui.checkout.CheckOutScreen
import com.example.shopmanagement.ui.home.HomeScreen
import com.example.shopmanagement.ui.home.SettingScreen
import com.example.shopmanagement.ui.login.SignInDestination
import com.example.shopmanagement.ui.login.SignInScreen
import com.example.shopmanagement.ui.login.SignUpDestination
import com.example.shopmanagement.ui.login.SignUpScreen
import com.example.shopmanagement.ui.product.ProductDetailDestination
import com.example.shopmanagement.ui.product.ProductDetailScreen

object Graph {
    const val ADMIN = "admin_graph"
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val AUTH = "auth_graph"

}

@Composable
fun RootShopNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Graph.AUTH,
        route = Graph.ROOT
    ) {
        composable(route = Graph.HOME) {
            ShopNavHost()
        }

        addAuthGraph(navController)

        composable(route = Graph.ADMIN) {
            AdminGraph()
        }
    }

}

fun NavGraphBuilder.addAuthGraph(navController: NavHostController) {
    navigation(startDestination = SignInDestination.route, route = Graph.AUTH) {
        composable(route = SignInDestination.route) {
            SignInScreen(
                navigateToSignUp = { navController.navigate(SignUpDestination.route) },
                navigateToHome = { navController.navigate(Graph.HOME) },
                navigateToAdmin = { navController.navigate(Graph.ADMIN) })
        }
        composable(route = SignUpDestination.route) {
            SignUpScreen(navigateToSignIn = {
                navController.navigate(SignInDestination.route)
            })
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopNavHost(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(

        topBar = {
            if (navController.previousBackStackEntry != null) {
                TopAppBar(
                    title = { /*TODO*/ },
                    navigationIcon = {

                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                        }

                    }
                )
            }
        },
        bottomBar = {
            NavigationBar {

                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route)
//                            {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
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
            modifier = Modifier.padding(paddingValues),
            route = Graph.HOME
        ) {

            composable(route = Screens.HomeScreen.name) {
                HomeScreen(navigateToProductDetails = {
                    navController.navigate("${ProductDetailDestination.route}/$it")
                })
            }
            composable(route = Screens.CartScreen.name) {
                ShoppingCartScreen(
                    navigateToAddressScreen = { navController.navigate(AddressScreenDestination.route) }
                )
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
                route = CheckOutDestination.routeWithArgs,
                arguments = listOf(navArgument(CheckOutDestination.addressIdArgs) {
                    type = NavType.StringType
                })
            ) {
                CheckOutScreen(
                    navigateToAddNewAddress = {
                        navController.navigate(
                            AddNewAddressScreenDestination.route
                        )
                    },
                    navigateToAddressScreen = {
                        navController.navigate(AddressScreenDestination.route)
                    }
                )
            }
            composable(route = AddressScreenDestination.route) {
                AddressScreen(navigateToAddNewAddress = {
                    navController.navigate(
                        AddNewAddressScreenDestination.route
                    )
                },
                    popBackStack = { navController.popBackStack() },
                    navigateToCheckOut = { navController.navigate("${CheckOutDestination.route}/$it") })
            }

            composable(route = AddNewAddressScreenDestination.route) {
                AddNewAddressPage()
            }
        }

    }
}


@Composable
fun AdminGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = HomeAdminScreenDestination.route) {
        composable(route = HomeAdminScreenDestination.route) {
            HomeAdminScreen(
                navigateToAddProduct = { navController.navigate(ProductAddDestination.route) },
                navigateToAddCategory = { navController.navigate(CategoryAddDestination.route) },
                navigateToAddBrand = { navController.navigate(BrandAddDestination.route) }
            )
        }

        composable(route = BrandAddDestination.route) {
            BrandAddScreen()
        }
        composable(route = ProductAddDestination.route) {
            ProductAddScreen()
        }
        composable(route = CategoryAddDestination.route) {
            CategoryAddScreen()
        }

    }
}
