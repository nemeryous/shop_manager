package com.example.shopmanagement.ui.admin.product

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.model.Product
import com.example.shopmanagement.ui.admin.brand.BrandAdminScreenDestination
import com.example.shopmanagement.ui.navigation.NavigationDestination


//object ProductAdminScreenDestination: NavigationDestination {

//@Composable
//fun  ProductAdminScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Column(
//            modifier = Modifier.weight(1f),
//            verticalArrangement = Arrangement.Top
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Start
//            ) {
//                IconButton(onClick = {  }) {
//                    Icon(Icons.Default.ArrowBackIosNew, contentDescription = null)
//                }
//                Spacer(modifier = Modifier.width(10.dp))
//                Text("All Product", style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Bold))
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//
//            LazyColumn {
//                items(cartItems) { product ->
//                    ProductCart(product = product)
//                }
//            }
//        }
//    }
//}
//@Composable
//fun ProductCart(product: CartItem, modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier
//            .padding(8.dp)
//            .clip(RoundedCornerShape(8.dp))
//            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
//    ) {
//        Row(
//            modifier = Modifier.padding(8.dp)
//        ) {
//            Image(
//                painter = painterResource(id = product.imageResourceId),
//                contentDescription = product.name,
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(bottom = 4.dp)) {
//                    Text(
//                        text = product.name,
//                        style = MaterialTheme.typography.titleLarge,
//                        modifier = Modifier
//                            .padding(bottom = 4.dp)
//                            .weight(1f)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Icon(Icons.Default.Delete, contentDescription = null)
//
//                }
//                Row {
//                    Text(text = "Black | size = 42",style = TextStyle(fontSize = 14.sp))
//                }
//                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp)) {
//                    Text(
//                        text = "$${product.price}",
//                        style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold),
//                        modifier = Modifier
//                            .padding(top = 4.dp)
//                            .weight(1f)
//                    )
//                    Spacer(modifier = Modifier.width(16.dp))
//                    QuantityButton(size = 30.dp)
//                }
//
//            }
//        }
//    }
//}
//@Composable
//fun QuantityButton(size: Dp) {
//    var quantity by remember { mutableIntStateOf(0) }
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.background(Color.Gray.copy(0.3f), RoundedCornerShape(30.dp))
//    ) {
//        IconButton(
//            onClick = { if (quantity > 0) quantity-- },
//            modifier = Modifier.size(size),
//            enabled = quantity > 0
//        ) {
//            Icon(Icons.Filled.Remove, contentDescription = "Decrease Quantity")
//        }
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(text = "$quantity", fontSize = 20.sp)
//        Spacer(modifier = Modifier.width(8.dp))
//        IconButton(
//            onClick = { quantity++ },
//            modifier = Modifier.size(size),
//            enabled = true
//        ) {
//            Icon(Icons.Filled.Add, contentDescription = "Increase Quantity")
//        }
//    }
//}

object ProductAdminScreenDestination : NavigationDestination {

    override val route: String = "product_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAdminScreen(
    viewModel: ProductAdminViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToProductAdd:() -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            uiState.productList.toList().forEach {
                ProductItem(product = it.second)
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.End)
        ) {
            FloatingActionButton(
                onClick = navigateToProductAdd,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = MaterialTheme.shapes.medium)
            ) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    }
}


@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(product.productImage).build(),
                contentDescription = product.productName,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = product.productName,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Default.Delete, contentDescription = null)

                }
                Row {
                    Text(text = "Black | size = 42", style = TextStyle(fontSize = 14.sp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text(
                        text = "$${product.productPrice}",
                        style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))

                }

            }
        }
    }
}

//@Composable
//fun ProductItemRow(cartItem: CartItem) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .padding(8.dp)
//    ) {
//        Image(
//            painter = painterResource(id = cartItem.imageResourceId),
//            contentDescription = null,
//            modifier = Modifier.size(80.dp),
//            contentScale = ContentScale.Crop
//        )
//        Column(
//            modifier = Modifier
//                .padding(start = 16.dp)
//                .weight(1f)
//        ) {
//            Text(text = cartItem.name, style = MaterialTheme.typography.titleLarge)
//            Text(text = "$${cartItem.price}", style = MaterialTheme.typography.titleLarge)
//            Text(text = "Quantity: ${cartItem.quantity}", style = MaterialTheme.typography.titleLarge)
//        }
//    }
//}
//
//@Preview(showSystemUi = true)
//@Composable
//fun PreviewProductAdminScreen() {
//    ProductAdminScreen()
