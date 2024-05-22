package com.example.shopmanagement.ui.admin.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopmanagement.ui.navigation.NavigationDestination

object OrderAdminScreenDestination : NavigationDestination {
    override val route: String = "order_admin"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun OrderAdminScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            OrderItemAdmin()
            OrderItemAdmin()
            OrderItemAdmin()
        }
    }
}

@Composable
fun OrderItemAdmin() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier
            .size(width = 450.dp, height = 240.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Order ID:",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Total Amount: ",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Status: ",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Shipping Address:",
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
                    Button(onClick = { }) {
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