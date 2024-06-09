package com.example.shopmanagement.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BrightnessAuto
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.DocumentScanner
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Output
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.shopmanagement.MainScreen
import com.example.shopmanagement.R
import com.example.shopmanagement.model.NavigationItem
import com.example.shopmanagement.ui.admin.BrandAddDestination
import com.example.shopmanagement.ui.admin.BrandAddScreen
import com.example.shopmanagement.ui.admin.CategoryAddDestination
import com.example.shopmanagement.ui.admin.CategoryAddScreen
import com.example.shopmanagement.ui.admin.ProductAddDestination
import com.example.shopmanagement.ui.admin.ProductAddScreen
import com.example.shopmanagement.ui.admin.brand.BrandAdminScreen
import com.example.shopmanagement.ui.admin.brand.BrandAdminScreenDestination
import com.example.shopmanagement.ui.admin.home.HomeAdminScreen
import com.example.shopmanagement.ui.admin.home.HomeAdminScreenDestination
import com.example.shopmanagement.ui.admin.order.OrderAdminScreen
import com.example.shopmanagement.ui.admin.order.OrderAdminScreenDestination
import com.example.shopmanagement.ui.admin.product.ProductAdminScreen
import com.example.shopmanagement.ui.admin.product.ProductAdminScreenDestination
import com.example.shopmanagement.ui.admin.user.AddUserAdminScreen
import com.example.shopmanagement.ui.admin.user.AddUserAdminScreenDestination
import com.example.shopmanagement.ui.admin.user.UserAdminScreen
import com.example.shopmanagement.ui.admin.user.UserScreenDestination
import com.example.shopmanagement.ui.cart.ShoppingCartScreen
import com.example.shopmanagement.ui.checkout.AddNewAddressPage
import com.example.shopmanagement.ui.checkout.AddNewAddressScreenDestination
import com.example.shopmanagement.ui.checkout.AddressScreen
import com.example.shopmanagement.ui.checkout.AddressScreenDestination
import com.example.shopmanagement.ui.checkout.CheckOutDestination
import com.example.shopmanagement.ui.checkout.CheckOutScreen
import com.example.shopmanagement.ui.home.HomeScreen
import com.example.shopmanagement.ui.login.SignInDestination
import com.example.shopmanagement.ui.login.SignInScreen
import com.example.shopmanagement.ui.login.SignUpDestination
import com.example.shopmanagement.ui.login.SignUpScreen
import com.example.shopmanagement.ui.order.OrderHistoryScreen
import com.example.shopmanagement.ui.product.ProductDetailDestination
import com.example.shopmanagement.ui.product.ProductDetailScreen
import com.example.shopmanagement.ui.profile.EditProfileScreen
import com.example.shopmanagement.ui.profile.EditProfileScreenDestination
import com.example.shopmanagement.ui.profile.LanguageScreen
import com.example.shopmanagement.ui.profile.LanguageScreenDestination
import com.example.shopmanagement.ui.profile.NotificationScreen
import com.example.shopmanagement.ui.profile.NotificationScreenDestination
import com.example.shopmanagement.ui.profile.ViewProfileScreen
import kotlinx.coroutines.launch

object Graph {
    const val ADMIN = "admin_graph"
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}

@Composable
fun RootShopNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Graph.MAIN,
        route = Graph.ROOT
    ) {
        composable(route = Graph.MAIN) {
        MainScreen(navController = navController)
        }
        composable(route = Graph.HOME) {
            ShopNavHost(navigateBackToAuth = { navController.navigate(Graph.AUTH) })
        }


        addAuthGraph(navController)

        composable(route = Graph.ADMIN) {
            AdminGraph(navigateBackToAuth = { navController.navigate(Graph.AUTH) })
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
    navigateBackToAuth: () -> Unit
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
            NavigationBar(
                modifier = Modifier.heightIn(30.dp),
                containerColor = Color.White
            ) {

                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                            navController.navigate(navItem.route)
                            {
                                popUpTo(navController.graph.findStartDestination().id) {
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
                            Text(
                                text = navItem.label,
                                style = if (currentDestination?.hierarchy?.any { it.route == navItem.route } == true) {
                                    MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                                } else {
                                    MaterialTheme.typography.labelSmall
                                }
                            )
                        },

                        modifier = Modifier.graphicsLayer(alpha = if (currentDestination?.hierarchy?.any { it.route == navItem.route } == true) {
                            1f
                        } else {
                            0.5f
                        })
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
            composable(route = Screens.OrderHistoryScreen.name) {
                OrderHistoryScreen()
            }

            composable(route = Screens.ProfileScreen.name) {
                ViewProfileScreen(
                    navigateToEditProfile = { navController.navigate(EditProfileScreenDestination.route) },

                    navigateToAddressScreen = { navController.navigate(AddressScreenDestination.route) },

                    navigateToNotification = { navController.navigate(NotificationScreenDestination.route) },

                    navigateBackToAuth = { navigateBackToAuth() },
                    navigateToLanguage = {navController.navigate(LanguageScreenDestination.route)}

                )
            }
            composable(route = LanguageScreenDestination.route) {
                LanguageScreen()
            }
            composable(route = EditProfileScreenDestination.route) {
                EditProfileScreen()
            }
            composable(route = NotificationScreenDestination.route) {
                NotificationScreen()
            }
            composable(
                route = ProductDetailDestination.routeWithArgs,
                arguments = listOf(navArgument(ProductDetailDestination.productIdArg) {
                    type = NavType.StringType
                })
            ) {
                ProductDetailScreen(navigateToCart = { navController.navigate(Screens.CartScreen.name) })
            }
            composable(
                route = CheckOutDestination.routeWithArgs,
                arguments = listOf(navArgument(CheckOutDestination.addressIdArgs) {
                    type = NavType.StringType
                })
            ) {
                CheckOutScreen(navigateToHome = { navController.navigate(Screens.HomeScreen.name) })
            }

            composable(route = AddressScreenDestination.route) {
                AddressScreen(navigateToAddNewAddress = {
                    navController.navigate(
                        AddNewAddressScreenDestination.route
                    )
                },
                    navigateToCheckOut = { navController.navigate("${CheckOutDestination.route}/$it") })
            }

            composable(route = AddNewAddressScreenDestination.route) {
                AddNewAddressPage()
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminGraph(
    navController: NavHostController = rememberNavController(),
    navigateBackToAuth: () -> Unit
) {

    val items = listOf(
        NavigationItem(
            title = "Trang chủ",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavigationItem(
            title = "Sản phẩm",
            selectedIcon = Icons.Filled.Category,
            unselectedIcon = Icons.Outlined.Category,
            badgeCount = 45
        ),
        NavigationItem(
            title = "Thương hiệu",
            selectedIcon = Icons.Filled.BrightnessAuto,
            unselectedIcon = Icons.Outlined.BrightnessAuto,
        ),
        NavigationItem(
            title = "Người dùng",
            selectedIcon = Icons.Filled.People,
            unselectedIcon = Icons.Outlined.People,
        ),
        NavigationItem(
            title = "Hoá đơn",
            selectedIcon = Icons.Filled.DocumentScanner,
            unselectedIcon = Icons.Outlined.DocumentScanner,
        ),
        NavigationItem(
            title = "Đăng xuất",
            selectedIcon = Icons.Filled.Output,
            unselectedIcon = Icons.Outlined.Output,
        ),
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
        var currentScreen by remember { mutableStateOf("Trang chủ") }

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Column {
                        Row(
                            modifier = Modifier
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Admin Avatar",
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Admin",
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    items.forEachIndexed { index, item ->

                        val navigateToItem: () -> Unit = when (item.title) {
                            "Trang chủ" -> {
                                {
                                    navController.navigate(HomeAdminScreenDestination.route)
                                    currentScreen = item.title
                                }
                            }

                            "Sản phẩm" -> {
                                {
                                    navController.navigate(ProductAdminScreenDestination.route)
                                    currentScreen = item.title
                                }
                            }

                            "Thương hiệu" -> {
                                {
                                    navController.navigate(BrandAdminScreenDestination.route)
                                    currentScreen = item.title
                                }
                            }

                            "Người dùng" -> {
                                {
                                    navController.navigate(UserScreenDestination.route)
                                    currentScreen = item.title
                                }
                            }

                            "Hoá đơn" -> {
                                {
                                    navController.navigate(OrderAdminScreenDestination.route)
                                    currentScreen = item.title
                                }
                            }

                            else -> {
                                {
                                    navigateBackToAuth()
                                }
                            }
                        }

                        NavigationDrawerItem(
                            label = {
                                Text(text = item.title)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                                navigateToItem()
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            badge = {
                                item.badgeCount?.let {
                                    Text(text = item.badgeCount.toString())
                                }
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            },
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = currentScreen)
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Default.Search, contentDescription = null)
                            }
                        }

                    )
                }
            ) { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = HomeAdminScreenDestination.route,
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(route = HomeAdminScreenDestination.route) {
                        HomeAdminScreen(
                            navigateToAddProduct = {
                                navController.navigate(
                                    ProductAddDestination.route
                                )
                            },
                            navigateToAddCategory = {
                                navController.navigate(
                                    CategoryAddDestination.route
                                )
                            },
                            navigateToAddBrand = {
                                navController.navigate(
                                    BrandAddDestination.route
                                )
                            },
                            navigateToProductDetails = {
                                navController.navigate("${ProductDetailDestination.route}/$it")
                            }
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

                    composable(route = ProductAdminScreenDestination.route) {
                        ProductAdminScreen(navigateToProductAdd = {
                            navController.navigate(
                                ProductAddDestination.route
                            )
                        })
                    }

                    composable(route = BrandAdminScreenDestination.route) {
                        BrandAdminScreen(navigateToBrandAdd = {
                            navController.navigate(
                                BrandAddDestination.route
                            )
                        })
                    }

                    composable(route = UserScreenDestination.route) {
                        UserAdminScreen(navigateToUserAdd = {
                            navController.navigate(AddUserAdminScreenDestination.route)
                        })
                    }
                    composable(route = AddUserAdminScreenDestination.route) {
                        AddUserAdminScreen()
                    }
                    composable(route = OrderAdminScreenDestination.route) {
                        OrderAdminScreen()
                    }
                    // moi them
                    composable(route = Screens.HomeScreen.name) {
                        HomeScreen(navigateToProductDetails = {
                            navController.navigate("${ProductDetailDestination.route}/$it")
                        })
                    }
                    composable(route = Screens.CartScreen.name) {
                        ShoppingCartScreen(
                            navigateToAddressScreen = {
                                navController.navigate(
                                    AddressScreenDestination.route
                                )
                            }
                        )
                    }
                    composable(route = Screens.OrderHistoryScreen.name) {
                        OrderHistoryScreen()
                    }
                    composable(
                        route = ProductDetailDestination.routeWithArgs,
                        arguments = listOf(navArgument(ProductDetailDestination.productIdArg) {
                            type = NavType.StringType
                        })
                    ) {
                        ProductDetailScreen(navigateToCart = { navController.navigate(Screens.CartScreen.name) })
                        ProductDetailScreen(navigateToCart = { navController.navigate(Screens.CartScreen.name) })
                    }

                    composable(
                        route = CheckOutDestination.routeWithArgs,
                        arguments = listOf(navArgument(CheckOutDestination.addressIdArgs) {
                            type = NavType.StringType
                        })
                    ) {
                        CheckOutScreen(navigateToHome = { navController.navigate(Screens.HomeScreen.name) })
                    }
                    composable(route = AddressScreenDestination.route) {
                        AddressScreen(navigateToAddNewAddress = {
                            navController.navigate(
                                AddNewAddressScreenDestination.route
                            )
                        },
                            navigateToCheckOut = { navController.navigate("${CheckOutDestination.route}/$it") })
                    }

                    composable(route = AddNewAddressScreenDestination.route) {
                        AddNewAddressPage()
                    }
                }
            }


        }

    }
}


