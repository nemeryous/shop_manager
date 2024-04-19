package com.example.shopmanagement.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
            HomeScreenHeader(name = "Phuong Nam")


            SearchComponent()


            HomeScreenBody()

            OutlinedButton(onClick = navigationToProductAdd ) {

            }
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {

            LeftHeader(
                name = "Phuong Nam",
                modifier = Modifier.padding(10.dp)
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
            painter = painterResource(id = R.drawable.android_superhero2),
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
            imageVector = Icons.Filled.Notifications,
            modifier = Modifier.size(40.dp)
        )

        IconComponent(
            imageVector = Icons.Filled.HeartBroken,
            modifier = Modifier.size(40.dp)
        )
    }

}
//@Preview
//@Composable
//fun HeaderPreview() {
//    HomeScreenHeader(name = "Phuong Nam")
//}

// End of Header Component

//Start of SearchField

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
                    modifier = Modifier.weight(1f)

                ) {
                    Text(
                        text = "25%",
                        modifier = Modifier,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = "Today's Special!",
                        modifier = Modifier,
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = "Get discount for every order, only valid for today",
                        modifier = Modifier,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.nitro_wallpaper_5000x2813),
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
fun BrandItem() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.nitro_wallpaper_5000x2813),
            contentDescription = "",
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.image_size))
                .padding(dimensionResource(id = R.dimen.padding_small))
                .clip(RoundedCornerShape(50.dp)),
            contentScale = ContentScale.Crop
        )
        Text(text = "Abc")
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
            BrandItem()
        }
        item {
            BrandItem()
        }
        item {
            BrandItem()
        }
        item {
            BrandItem()
        }
        item {
            BrandItem()
        }
    }
}

@Composable
fun PopularBrandTag(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
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
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(Brands.brands) { brand ->
                BrandTag(value = brand.brandName)
            }
        }
    }

}

@Composable
fun BrandTag(value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray),
    ) {
        Text(
            text = value, modifier = Modifier.padding(
                horizontal = 16.dp, vertical = 8.dp
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenBodyPreview() {
    BrandTag("All")
}

//End of Body


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navigationToProductAdd = {})
}