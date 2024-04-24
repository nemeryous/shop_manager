package com.example.shopmanagement.ui.admin

data class ProductAddUiState(
    val productName: String = "",
    val productQuantity: String = "",
    val productPrice: String = "",
    val productDescription: String = "",
    val productImage: String = "",

    val selectedCategory: String = ""
    )
