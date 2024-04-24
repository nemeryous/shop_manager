package com.example.shopmanagement.extension

import com.example.shopmanagement.model.Product
import com.example.shopmanagement.ui.admin.ProductAddUiState

fun ProductAddUiState.toProduct(): Product = Product(
    productName = productName,
    productQuantity = productQuantity.toIntOrNull()?:0,
    productPrice = productPrice.toDoubleOrNull()?:0.0,
    productDescription = productDescription,
    productImage = productImage
)