package com.example.shopmanagement.ui.admin.brand

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
import com.example.shopmanagement.R
import com.example.shopmanagement.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object BrandAdminScreenDestination : NavigationDestination {
    override val route: String = "brand_admin"
    override val titleRes: Int = R.string.brand_admin

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandAdminScreen() {

}