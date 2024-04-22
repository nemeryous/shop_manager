package com.example.shopmanagement.extension

import com.example.shopmanagement.model.Product
import com.example.shopmanagement.ui.admin.ProductAddUiState

fun ProductAddUiState.toProduct(): Product = Product(
    productName = productName,
    productQuantity = productQuantity
)