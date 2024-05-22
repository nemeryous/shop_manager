package com.example.shopmanagement.ui.checkout

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.R
import com.example.shopmanagement.model.ShippingAddress
import com.example.shopmanagement.ui.navigation.NavigationDestination
import com.example.shopmanagement.ui.theme.md_theme_light_background
import kotlinx.coroutines.flow.update

object AddressScreenDestination : NavigationDestination {
    override val route: String = "address"
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}
@Composable
fun AddressScreen(
    viewModel: AddressScreenViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToAddNewAddress:() -> Unit,
    navigateToCheckOut:(String) -> Unit
) {
    val shippingAddressList by viewModel.shippingAddressList.collectAsState()
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
                Spacer(modifier = Modifier.width(10.dp))
                Text("Shipping Address", style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))

            shippingAddressList.forEach { shippingAddress ->
                Address(shippingAddress = shippingAddress, modifier = Modifier.clickable {
                    navigateToCheckOut(shippingAddress.id)
                })
                Spacer(modifier = Modifier.height(16.dp))
            }

            OutlinedButton(onClick = { navigateToAddNewAddress() }) {
                Text(text = "Add New Address")
            }


//            LazyColumn {
//                item {
//                   Address()
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Address()
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Address()
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Address()
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Address()
//                    Spacer(modifier = Modifier.height(16.dp))
//                }
//            }
        }

    }
}
@Composable
fun Address(shippingAddress: ShippingAddress, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
    )
    {
        Row(modifier = Modifier.padding(8.dp)) {

            IconButton(onClick = { /*TODO*/ }) {
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
        }
    }
}
@Composable
fun AddNewAddressButton() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
        .padding(horizontal = 16.dp)) {
        Button(
            onClick = { },
            modifier = Modifier
                .height(50.dp)
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            )
        ) {
            Text("Add New Address")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewAddressScreen() {
    AddressScreen(navigateToAddNewAddress = {}, navigateToCheckOut = {})
}
