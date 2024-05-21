package com.example.shopmanagement.ui.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.ui.navigation.NavigationDestination


object AddNewAddressScreenDestination: NavigationDestination {
    override val route: String = "add_new_address"

    override val titleRes: Int
        get() = TODO("Not yet implemented")
}
@Composable
fun AddNewAddressPage(
    shippingViewModel: ShippingAddressViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by shippingViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = "Liên hệ")
        OutlinedTextField(
            value = uiState.shippingName,
            onValueChange = { shippingViewModel.updateName(it) },
            label = { Text("Họ và tên") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = uiState.shippingPhone,
            onValueChange = shippingViewModel::updatePhone,
            label = { Text("Số điện thoại") },
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Địa chỉ")
        OutlinedTextField(
            value = uiState.shippingStreet,
            onValueChange = shippingViewModel::updateStreet,
            label = { Text("Tên đường") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.shippingCommune,
            onValueChange = shippingViewModel::updateCommune,
            label = { Text("Xã/phường") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.shippingDistrict,
            onValueChange = shippingViewModel::updateDistrict,
            label = { Text("Quận/huyện") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.shippingCity,
            onValueChange = shippingViewModel::updateCity,
            label = { Text("Tỉnh thành phố") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                shippingViewModel.savedAddress()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Hoàn thành")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddNewAddressPreview() {
    AddNewAddressPage()
}

