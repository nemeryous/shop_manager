package com.example.shopmanagement.ui.checkout

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Output
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopmanagement.R
import com.example.shopmanagement.model.Cart
import com.example.shopmanagement.model.Cart.getListProduct
import com.example.shopmanagement.ui.cart.PriceBar
import com.example.shopmanagement.ui.cart.ProductCart

@Composable
fun CheckOutScreen() {
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    stringResource(id = R.string.my_cart),
                    style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Bold)
                )
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Search, contentDescription = "Search Icon")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(getListProduct()) { item ->
                    ProductCart(
                        item = item,
                        increaseQuantity = {  },
                        decreaseQuantity = {  })
                }
            }
        }
        PriceBar(price = Cart.totalPrice.toString(), border = false) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.check_out), fontSize = 17.sp)
                Spacer(modifier = Modifier.width(4.dp))
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
fun () {
    
}