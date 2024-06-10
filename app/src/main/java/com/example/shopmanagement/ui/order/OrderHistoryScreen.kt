package com.example.shopmanagement.ui.order

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.model.Order

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryScreen(
    viewModel: OrderHistoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToOrderDetails: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Order") },
                navigationIcon = {
                    IconButton({}) {
                        Icon(Icons.Default.CardGiftcard, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Search, contentDescription = "")
                    }
                }
            )
        }
    ) { it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(uiState.userOrderList) { order ->
                OrderItem(order, navigateToOrderDetails = navigateToOrderDetails)
            }
        }
    }

}

@Composable
fun OrderItem(order: Order, navigateToOrderDetails: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                navigateToOrderDetails(order.orderId)
            }
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

@Composable
fun ColumnDetails(title: String, details: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)

        Text(text = details, style = MaterialTheme.typography.bodyLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun MyOrderScreenPreview() {
    OrderHistoryScreen(navigateToOrderDetails = {})
}

@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    ColumnDetails(title = "Order ID:", details = "123345")
}