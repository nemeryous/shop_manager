package com.example.shopmanagement.ui.admin.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.model.Order
import com.example.shopmanagement.ui.navigation.NavigationDestination

object OrderAdminScreenDestination : NavigationDestination {
    override val route: String = "order_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun OrderAdminScreen(
    orderAdminViewModel: OrderAdminViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by orderAdminViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        items(uiState.orderList) {order ->
            OrderItemAdmin(order, updateStatus = { orderAdminViewModel.updateStatus(order.orderId) })
        }
    }
}

@Composable
fun OrderItemAdmin(
    order: Order,
    updateStatus:() -> Unit
) {

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Order ID: ${order.orderId}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total Amount: ${order.totalPrice}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Status: ${order.status}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Shipping Address: ${order.shippingAddress.address}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box {
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(onClick = { }) {
                        Text(text = "View Details")
                    }
                    Button(onClick = { updateStatus() }) {
                        Text(text = "Xác nhận")
                    }
                }
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderAdminScreenPreview() {
    OrderAdminScreen()
}