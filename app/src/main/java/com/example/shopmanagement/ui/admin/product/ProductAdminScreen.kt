package com.example.shopmanagement.ui.admin.product

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopmanagement.model.CartItem
import com.example.shopmanagement.model.cartItems

@Composable
fun  ProductAdminScreen() {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = {  }) {
                    Icon(Icons.Default.ArrowBackIosNew, contentDescription = null)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text("All Product", style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Bold))
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(cartItems) { product ->
                    ProductCart(product = product)
                }
            }
        }
    }
}
@Composable
fun ProductCart(product: CartItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageResourceId),
                contentDescription = product.name,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.padding(bottom = 4.dp)) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(Icons.Default.Delete, contentDescription = null)

                }
                Row {
                    Text(text = "Black | size = 42",style = TextStyle(fontSize = 14.sp))
                }
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp)) {
                    Text(
                        text = "$${product.price}",
                        style = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    QuantityButton(size = 30.dp)
                }

            }
        }
    }
}
@Composable
fun QuantityButton(size: Dp) {
    var quantity by remember { mutableIntStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.background(Color.Gray.copy(0.3f), RoundedCornerShape(30.dp))
    ) {
        IconButton(
            onClick = { if (quantity > 0) quantity-- },
            modifier = Modifier.size(size),
            enabled = quantity > 0
        ) {
            Icon(Icons.Filled.Remove, contentDescription = "Decrease Quantity")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "$quantity", fontSize = 20.sp)
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = { quantity++ },
            modifier = Modifier.size(size),
            enabled = true
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Increase Quantity")
        }
    }
}
@Composable
fun ProductItemRow(cartItem: CartItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = cartItem.imageResourceId),
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(text = cartItem.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "$${cartItem.price}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Quantity: ${cartItem.quantity}", style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewProductAdminScreen() {
    ProductAdminScreen()
}