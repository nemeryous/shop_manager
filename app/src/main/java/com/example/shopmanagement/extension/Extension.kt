package com.example.shopmanagement.extension

import com.example.shopmanagement.model.Product
import com.example.shopmanagement.ui.product.ProductUiState

fun ProductUiState.toProduct(): Product = Product(
    productName = productName,
    productQuantity = productQuantity
)