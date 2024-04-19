package com.example.shopmanagement.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopmanagement.AppViewModelProvider
import com.example.shopmanagement.extension.toProduct
import com.example.shopmanagement.ui.navigation.NavigationDestination


object ProductAddDestination : NavigationDestination {
    override val route: String = "product_add"

    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

@Composable
fun ProductAddScreen(
    productViewModel: ProductViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val productUiState by productViewModel.productUiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {

            OutlinedTextField(
                value = productUiState.productName,
                onValueChange = productViewModel::updateProductName,
                label = {
                    Text(text = "name")
                }
            )
            OutlinedTextField(value = productUiState.productQuantity.toString(),
                onValueChange = productViewModel::updateProductQuantity,
                label = {
                    Text(text = "quantity")
                })
            OutlinedButton(onClick = {
                productViewModel.addProduct(productUiState.toProduct())
            }) {

            }
        }
    }
}

@Preview
@Composable
fun ProductAddPreview() {
    ProductAddScreen()
}