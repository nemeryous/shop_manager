package com.example.shopmanagement.ui.admin.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.shopmanagement.ui.admin.brand.BrandAdminScreenDestination
import com.example.shopmanagement.ui.navigation.NavigationDestination

object OrderAdminScreenDestination : NavigationDestination {
    override val route: String = "order_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderAdminScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = BrandAdminScreenDestination.titleRes))
                },
                navigationIcon = {
                    IconButton(onClick = {

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
    ) { it ->
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Order")
        }

    }
}