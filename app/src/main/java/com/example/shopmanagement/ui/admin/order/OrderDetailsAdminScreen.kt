package com.example.shopmanagement.ui.admin.order

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.model.Item
import com.example.shopmanagement.model.Order
import com.example.shopmanagement.ui.navigation.NavigationDestination
import com.example.shopmanagement.ui.order.ColumnDetails

object OrderDetailsAdminScreenDestination: NavigationDestination {
    override val route: String = "order_admin_details"
    const val orderAdminIdArgs = "orderId"
    val routeWithArgs = "$route/{$orderAdminIdArgs}"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}
@Composable
fun OrderDetailsAdminScreen(
    viewModel: OrderDetailsAdminViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OrderItemDetailsAdmin(order = uiState.order)

        LazyColumn {
            items(uiState.order.cartItem) { item ->
                ProductItemAdmin(
                    item = item,
                ) {

                }
            }
        }

    }
}
@Composable
fun ProductItemAdmin(
    item: Item,
    modifier: Modifier = Modifier,
    deleteItem: () -> Unit
) {
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
                    .data(item.product.productImage).build(),
                contentDescription = item.product.productName,
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
                        text = item.product.productName,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                }
                Row {
                    Text(text = "Size: ${item.size}", style = TextStyle(fontSize = 14.sp))
                }
                Row {
                    Text(text = "Quantity: ${item.quantity}", style = TextStyle(fontSize = 14.sp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    Text(
                        text = "$${item.product.productPrice}",
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
@Composable
fun OrderItemDetailsAdmin(order: Order) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            ColumnDetails(title = "Order ID: ", details = order.orderId)
            Spacer(modifier = Modifier.height(8.dp))

            ColumnDetails(title = "Total Amount: ", details = "$${order.totalPrice}")
            Spacer(modifier = Modifier.height(8.dp))

            ColumnDetails(title = "Status: ", details = order.status)
            Spacer(modifier = Modifier.height(8.dp))

            ColumnDetails(title = "Shipping address: ", details = order.shippingAddress.address)
            Spacer(modifier = Modifier.height(8.dp))

        }

        Divider()
    }
}