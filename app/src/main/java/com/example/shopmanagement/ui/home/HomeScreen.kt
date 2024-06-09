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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Card
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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

@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
    navigateToProductDetails: (String) -> Unit,
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),

    ) {

    val homeScreenUiState by homeScreenViewModel.homeScreenUiState.collectAsState()

    Scaffold(
        modifier = modifier
            .background(color = Color.White)
            .padding(14.dp)
            .fillMaxSize(),

        ) { it ->
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .padding(
                    it
                )
                .verticalScroll(rememberScrollState()),

            ) {
            HomeScreenHeader(name = stringResource(id = R.string.select_store))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp, vertical = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                SearchBarM3(
                    searchQuery = homeScreenUiState.searchQuery,
                    onChangeSearchQuery = homeScreenViewModel::onChangeSearchQuery,
                    findProductByName = homeScreenViewModel::findProductByName
                )
            }
            HomeScreenBody(
                productList = homeScreenUiState.productList,
                navigateToProductDetails = navigateToProductDetails,
                brandList = homeScreenUiState.brandList,
                findProductByBrand = homeScreenViewModel::findProductByBrand
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
                name = "SHOE SHOP",
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
                .clip(RoundedCornerShape(60.dp))
                .wrapContentSize(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            text = name,
            style = MaterialTheme.typography.displaySmall
        )
    }

}

@Composable
fun RightHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        IconComponent(
            imageVector = Icons.Filled.NotificationsNone,
            modifier = Modifier.size(30.dp)
        )

        IconComponent(
            imageVector = Icons.Filled.FavoriteBorder,
            modifier = Modifier.size(30.dp)
        )
    }

}

@Composable
fun SearchComponent() {
    TextField(
        value = "",
        onValueChange = {},
        label = {
            Text(
                text = "Search",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight(200))
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 15.dp
            )
            .clip(shape = RoundedCornerShape(15.dp))
            .clearAndSetSemantics { },
//            .blur(radius = 1.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "")
        },

        trailingIcon = {
            Icon(imageVector = Icons.Filled.FilterList, contentDescription = "")
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarM3(
    modifier: Modifier = Modifier,
    searchQuery: String, onChangeSearchQuery: (String) -> Unit,
    findProductByName:() -> Unit
) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val searchHistory = listOf("Android", "Chat GPT4", "Sneakers")
    DockedSearchBar(
        query = searchQuery,
        onQueryChange = onChangeSearchQuery,
        onSearch = { newQuery ->
            findProductByName()
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = {
            Text("Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        trailingIcon = if (active) {
            {
                IconButton(onClick = {
                    if (searchQuery.isNotEmpty()) onChangeSearchQuery("") else active = false
                }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = null)
                }
            }

        } else null
    ) {
        searchHistory.takeLast(3).forEach { item ->

            ListItem(modifier = Modifier.clickable { query = item },
                headlineContent = { Text(text = item, fontSize = 16.sp) },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Filled.History,
                        contentDescription = null
                    )
                }

            )
        }
    }
}
//End of SearchField

//Start of Body

@Composable
fun HomeScreenBody(
    modifier: Modifier = Modifier,
    productList: Map<String, Product>,
    navigateToProductDetails: (String) -> Unit,
    brandList: List<Brand>,
    findProductByBrand: (String) -> Unit
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
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "See all",
                style = MaterialTheme.typography.titleLarge
            )
        }

        HomeBodyBanner()

        GridBrandItem(brandList)

        PopularBrandTag(
            productList = productList,
            navigateToProductDetails = navigateToProductDetails,
            brandList = brandList,
            findProductByBrand = findProductByBrand
        )
    }
}

@Composable
fun HomeBodyBanner(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 7.dp)
            .heightIn(180.dp)
            .shadow(4.dp, RoundedCornerShape(30.dp)),
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(0xFFB3B4B6))
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(20.dp)

                ) {
                    Text(
                        text = "25%",
                        modifier = Modifier,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "Today's Special!",
                        modifier = Modifier,
                        style = MaterialTheme.typography.labelMedium
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
                        .padding(end = 10.dp)
                        .heightIn(180.dp)
                        .weight(1f)
                        .size(dimensionResource(id = R.dimen.image_size))
                        .clip(shape = RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}


@Composable
fun GridBrandItem(
    brandList: List<Brand>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 200.dp)
    ) {
        items(brandList) {
            BrandItem(imageUrl = it.brandImageUrl, brandName = it.brandName)
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
        Box() {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageUrl)
                    .build(),
                contentDescription = "",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_size))
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clip(CircleShape)
                    .wrapContentSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = brandName,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Composable
fun PopularBrandTag(
    modifier: Modifier = Modifier,
    productList: Map<String, Product>,
    navigateToProductDetails: (String) -> Unit,
    brandList: List<Brand>,
    findProductByBrand: (String) -> Unit
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
                BrandTag(value = brand.brandName, findProductByBrand = findProductByBrand)
            }
        }
        ProductList(productList = productList, navigateToProductDetails = navigateToProductDetails)

    }
}

@Composable
fun BrandTag(value: String, modifier: Modifier = Modifier, findProductByBrand: (String) -> Unit) {
    Box(
        modifier = modifier
            .padding(vertical = 12.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(2.dp, Color.Black, CircleShape)
            .wrapContentSize()
            .clickable {
                findProductByBrand(value)
            }
    ) {
        Text(
            text = value,
            modifier = modifier
                .padding(horizontal = 12.dp, vertical = 10.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    productList: Map<String, Product>,
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
                ProductItem(
                    product = product.second,
                    productId = product.first,
                    navigateToProductDetails = navigateToProductDetails
                )
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
    val roundedRating = Math.round(product.rating * 10) / 10
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .heightIn(200.dp)
            .heightIn(250.dp)
            .wrapContentSize(),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    navigateToProductDetails(productId)
                },
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Box {
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
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.productName,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 1.dp, vertical = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.StarHalf,
                    contentDescription = null,
                    tint = Color(0xFF1D1C1C)
                )
                Text(
                    text = "$roundedRating |",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight(500)),
                )
                Text(
                    text = "$${product.productPrice}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 4.dp)
                )
            }

        }
    }
}
