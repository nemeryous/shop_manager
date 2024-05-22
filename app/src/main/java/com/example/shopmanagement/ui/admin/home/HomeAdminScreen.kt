package com.example.shopmanagement.ui.admin.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopmanagement.R
import com.example.shopmanagement.model.ItemHomeAdmin
import com.example.shopmanagement.model.itemHomeAdmin
import com.example.shopmanagement.ui.navigation.NavigationDestination


object HomeAdminScreenDestination : NavigationDestination {
    override val route: String = "home_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")

}

@Composable
fun HomeAdminScreen(
    navigateToAddProduct: () -> Unit,
    navigateToAddBrand:() -> Unit,
    navigateToAddCategory:() -> Unit,
) {

}



@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategoryScreenPreview() {
    HomeAdminScreen(navigateToAddProduct = {}, navigateToAddBrand = {}, navigateToAddCategory =  {})
}
