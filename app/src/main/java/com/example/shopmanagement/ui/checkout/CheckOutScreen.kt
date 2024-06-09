package com.example.shopmanagement.ui.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BorderColor
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Output
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.R
import com.example.shopmanagement.model.CartItem
import com.example.shopmanagement.model.ShippingAddress
import com.example.shopmanagement.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch


object CheckOutDestination : NavigationDestination {
    override val route: String = "check_out"
    const val addressIdArgs = "addressId"
    val routeWithArgs = "$route/{$addressIdArgs}"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun CheckOutScreen(
    viewmodel: CheckOutViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {


    val uiState by viewmodel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text("Checkout", style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                item {
                    Text(
                        text = "Shopping Address",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    CartAddress(
                        shippingAddress = uiState.selected,
                        navigateToAddressScreen = { /*TODO*/ }) {

                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Order List",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(viewmodel.getListProduct()) { product ->
                    ProductCheckOut(product = product)
                }
                item {
                    CartCheckout(amount = viewmodel.calculateTotalPrice())
                }
            }
        }

        PriceBar(
            border = false
        ) {
            Row(
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        viewmodel.order()
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.check_out), fontSize = 17.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Output,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }

}

@Composable
fun ProductCheckOut(product: CartItem, modifier: Modifier = Modifier) {
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
                    .data(product.product.productImage).build(),
                contentDescription = product.product.productName,
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
                        text = product.product.productName,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .weight(1f)
                    )
                }
                Row {
                    Text(text = "Black | size = 42", style = TextStyle(fontSize = 14.sp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text(
                        text = "$${product.product.productPrice}",
                        style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
//                    QuantityButton(size = 30.dp)
                }

            }
        }
    }
}

@Composable
fun CartAddress(
    shippingAddress: ShippingAddress,
    navigateToAddressScreen: () -> Unit,
    modifier: Modifier = Modifier,
    testShared: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    )
    {
        Row(modifier = Modifier.padding(8.dp)) {

            IconButton(onClick = { testShared() }) {
                Icon(Icons.Default.AddLocation, contentDescription = null)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "${shippingAddress.shippingName}, ${shippingAddress.shippingPhone}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .weight(1f),
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    Text(text = shippingAddress.address, style = TextStyle(fontSize = 14.sp))
                }

            }
            IconButton(onClick = { navigateToAddressScreen() }) {
                Icon(Icons.Default.BorderColor, contentDescription = null)
            }
        }
    }
}

@Composable
fun Ship(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    )
    {
        Row(modifier = Modifier.padding(8.dp)) {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.CarCrash, contentDescription = null)
            }

            Spacer(modifier = Modifier.width(6.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "Choose Shipping Type",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .weight(1f),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ChevronRight, contentDescription = null)
            }
        }
    }
}

@Composable
fun CartCheckout(modifier: Modifier = Modifier, amount: Double, shipping: Int = 15) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .width(500.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    )
    {
        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 10.dp, top = 10.dp)
            )
            {
                Text(
                    text = "Amount",
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "$$amount")

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 10.dp)
            )
            {
                Text(
                    text = "Shipping",
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "$$shipping")

            }
            Spacer(modifier = Modifier.height(16.dp))

            Divider(modifier = Modifier.fillMaxWidth())

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 16.dp, end = 10.dp)
            )
            {
                Text(
                    text = "Total",
                    style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(text = "$${amount + shipping}")

            }
        }
    }
}

@Composable
fun PriceBar(
    modifier: Modifier = Modifier,
    border: Boolean = true,
    actionButtonContent: @Composable () -> Unit,
) {

    Column(
        verticalArrangement = if (border) Arrangement.Center else Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
            .let {
                if (border)
                    it
                        .background(
                            Color.White, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .border(
                            border = BorderStroke(2.dp, Color.Gray),
                            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                        )
                        .height(100.dp)
                else it
                    .background(Color.White)
                    .height(80.dp)
            }
    ) {
        if (!border) HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(horizontal = 16.dp)
        ) {
            Box {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    onClick = { },
                    modifier = Modifier
                        .height(50.dp)
                        .width(300.dp)
                ) {
                    actionButtonContent()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckOutScreen() {
    CheckOutScreen()
}