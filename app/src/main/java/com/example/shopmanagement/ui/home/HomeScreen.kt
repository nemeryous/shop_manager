package com.example.shopmanagement.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopmanagement.R
import com.example.shopmanagement.model.Brands
import com.example.shopmanagement.model.ProductCatalog
import com.example.shopmanagement.model.ProductHome
import com.example.shopmanagement.ui.components.IconComponent
import com.example.shopmanagement.ui.navigation.NavigationDestination


object HomeScreenDestination : NavigationDestination {
    override val route: String = "home_screen"

    override val titleRes: Int = R.string.home_screen

}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigationToProductAdd: () -> Unit
) {
    Surface(
        modifier = modifier
            .background(Color.White)
            .padding(10.dp)
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 10.dp
                )
                .verticalScroll(rememberScrollState()),

            ) {
            HomeScreenHeader(name = "SELECT STORE")
            SearchComponent()
            HomeScreenBody()
//            OutlinedButton(onClick = navigationToProductAdd) {
//
//            }
        }
    }
}

// Start Header Component
@Composable
fun HomeScreenHeader(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            LeftHeader(
                name = "SELECT STORE",
                modifier = Modifier.padding(5.dp)
            )
            RightHeader(modifier = Modifier.padding(10.dp))

        }


    }
}

@Composable
fun LeftHeader(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .clip(RoundedCornerShape(50.dp))
                .wrapContentSize(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
    }

}

@Composable
fun RightHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        IconComponent(
            imageVector = Icons.Filled.NotificationsNone,
            modifier = Modifier.size(40.dp)
        )

        IconComponent(
            imageVector = Icons.Filled.FavoriteBorder,
            modifier = Modifier.size(40.dp)
        )
    }

}
@Composable
fun SearchComponent() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(text = "Search")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 15.dp
            ),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
        },

        trailingIcon = {
            Icon(imageVector = Icons.Filled.FilterList, contentDescription = "")
        }

    )
}

//End of SearchField

//Start of Body

@Composable
fun HomeScreenBody(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Special Offer",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            Text(
                text = "See all",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        HomeBodyBanner()

        GridBrandItem()

        PopularBrandTag()


    }
}

@Composable
fun HomeBodyBanner(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(200.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)


        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(15.dp)

                ) {
                    Text(
                        text = "25%",
                        modifier = Modifier,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = "Today's Special!",
                        modifier = Modifier,
                        style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Get discount for every order, only valid for today",
                        modifier = Modifier,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.banner),
                    contentDescription = "",
                    modifier = Modifier
                        .heightIn(200.dp)
                        .weight(1f)
                        .heightIn()
                        .size(dimensionResource(id = R.dimen.image_size))
                        .clip(shape = RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop,


                    )
            }
        }
    }
}

@Composable
fun BrandItem(
    imageName: Int,
    brandName: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageName),
            contentDescription = "",
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .padding(dimensionResource(id = R.dimen.padding_small))
                .clip(RoundedCornerShape(50.dp)),
            contentScale = ContentScale.Crop
        )
        Text(text = brandName)
    }
}


@Composable
fun GridBrandItem() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 200.dp)
    ) {
        item {
            BrandItem(
                imageName = R.drawable.nike,
                brandName = "Nike"
            )
        }
        item {
            BrandItem(
                imageName = R.drawable.adidas,
                brandName = "Adidas"
            )
        }
        item {
            BrandItem(
                imageName = R.drawable.puma,
                brandName = "Puma"
            )
        }
        item {
            BrandItem(
                imageName = R.drawable.acics,
                brandName = "Asics"
            )
        }
        item {
            BrandItem(
                imageName = R.drawable.reebok,
                brandName = "Reebok"
            )
        }
        item {
            BrandItem(
                imageName = R.drawable.balance,
                brandName = "New balance"
            )
        }
        item {
            BrandItem(
                imageName = R.drawable.converse,
                brandName = "Converse"
            )
        }
        item {
            BrandItem(
                imageName = R.drawable.more,
                brandName = "More"
            )
        }

    }
}

@Composable
fun PopularBrandTag(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth().height(600.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.most_popular),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.all),
                style = MaterialTheme.typography.titleLarge
            )
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 12.dp)
        ) {
            items(Brands.brands) { brand ->
                BrandTag(value = brand.brandName)
            }
        }
        ProductList()
    }
}

@Composable
fun BrandTag(value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 12.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, Color.Black, CircleShape)
    ) {
        Text(
            text = value,
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
@Composable
fun ProductList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "New Arrivals",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 12.dp)
            )
            Text(
                text = "View All",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 8.dp).height(500.dp)
        ) {
            items(ProductCatalog.catalog) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Composable
fun ProductItem(product: ProductHome, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}
//@Preview
//@Composable
//fun PreviewList(){
//    ProductList()
//}
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenBodyPreview() {
//    BrandTag("All")
//}
//End of Body


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navigationToProductAdd = {})
}