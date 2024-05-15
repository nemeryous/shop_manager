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
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.TopStart
            )
            Text(
                text = "HOME ADMIN",
                style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        ) {
            items(itemHomeAdmin) { item ->
                val imageVector: ImageVector = when (item.title) {
                    "Add Product", "Add Brand", "Add Category" -> Icons.Filled.Add
                    "All Product", "All Brand", "All Category" -> Icons.Filled.RemoveRedEye
                    else -> Icons.Filled.Output
                }
                val navigateToItem: () -> Unit = when (item.title) {
                    "Add Product" -> navigateToAddProduct
                    "Add Brand" -> navigateToAddBrand
                    "Add Category" -> navigateToAddCategory
                    else -> navigateToAddProduct
                }
                Item(
                    items = item,
                    itemIcon = imageVector,
                    navigateToItem = navigateToItem
                )
            }

        }
    }
}

@Composable
fun Item(
    items: ItemHomeAdmin,
    itemIcon: ImageVector,
    modifier: Modifier = Modifier,
    navigateToItem: () -> Unit
) {

    Box(
        modifier = Modifier
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(12.dp)
            )
            .aspectRatio(1.5f)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                navigateToItem()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = itemIcon, contentDescription = null)
            }

            Text(
                items.title, style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            )
        }


    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategoryScreenPreview() {
    HomeAdminScreen(navigateToAddProduct = {}, navigateToAddBrand = {}, navigateToAddCategory =  {})
}
