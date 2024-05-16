package com.example.shopmanagement.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.R
import com.example.shopmanagement.model.Brand
import com.example.shopmanagement.model.Product
import com.example.shopmanagement.ui.components.IconComponent
import com.example.shopmanagement.ui.navigation.BottomBarItem

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    navigateToProductDetails: (String) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val homeScreenUiState by homeScreenViewModel.homeScreenUiState.collectAsState()

    Scaffold(
        modifier = modifier
            .background(Color.White)
            .padding(10.dp)
            .fillMaxSize(),

    ) { it ->
        Column(
            modifier = Modifier
                .padding(
                    it
                )
                .verticalScroll(rememberScrollState()),

            ) {
            HomeScreenHeader(name = stringResource(id = R.string.select_store))
            SearchComponent()
            HomeScreenBody(
                productList = homeScreenUiState.productList,
                navigateToProductDetails = navigateToProductDetails,
                brandList = homeScreenUiState.brandList
            )
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
fun HomeScreenBody(
    modifier: Modifier = Modifier,
    productList: Map<String,Product>,
    navigateToProductDetails: (String) -> Unit,
    brandList: List<Brand>
) {

    Column(
        modifier = modifier.wrapContentHeight(),
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

        GridBrandItem(productList)

        PopularBrandTag(
            productList = productList,
            navigateToProductDetails = navigateToProductDetails,
            brandList = brandList

        )

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
    imageUrl: String,
    brandName: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .build(),
            contentDescription = "",
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .padding(dimensionResource(id = R.dimen.padding_small))
                .clip(RoundedCornerShape(50.dp)),
            contentScale = ContentScale.Crop
        )
//        Image(
//            painter = painterResource(id = imageName),
//            contentDescription = "",
//            modifier = Modifier
//                .size(dimensionResource(id = R.dimen.image_size))
//                .padding(dimensionResource(id = R.dimen.padding_small))
//                .clip(RoundedCornerShape(50.dp)),
//            contentScale = ContentScale.Crop
//        )
        Text(text = brandName)
    }
}


@Composable
fun GridBrandItem(
    productList: Map<String,Product>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 100.dp)
    ) {

        items(productList.toList()) {

            BrandItem(imageUrl = it.second.productImage, brandName = it.second.brand)

        }

    }
}

@Composable
fun PopularBrandTag(
    modifier: Modifier = Modifier,
    productList: Map<String,Product>,
    navigateToProductDetails: (String) -> Unit,
    brandList: List<Brand>
) {

    Column(
        modifier = modifier
            .wrapContentSize()

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
            items(brandList) { brand ->
                BrandTag(value = brand.brandName)
            }
        }
        ProductList(productList = productList, navigateToProductDetails = navigateToProductDetails)

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
fun ProductList(
    modifier: Modifier = Modifier,
    productList: Map<String,Product>,
    navigateToProductDetails: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()

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
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .heightIn(max = 500.dp)
        ) {
            items(productList.toList()) { product ->
                ProductItem(product = product.second, productId = product.first, navigateToProductDetails = navigateToProductDetails)
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product, modifier: Modifier = Modifier,
    productId: String,
    navigateToProductDetails: (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .height(300.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navigateToProductDetails(productId)
                },
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(product.productImage)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
//            Image(
//                painter = painterResource(id = product.imageRes),
//                contentDescription = product.name,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(180.dp)
//                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop
//            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.productName,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "$${product.productPrice}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}
