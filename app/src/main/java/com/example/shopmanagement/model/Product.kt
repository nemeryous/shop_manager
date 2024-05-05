package com.example.shopmanagement.model

data class Product(
    val productName: String = "",
    val productQuantity: Int = 0,
    val productPrice: Double = 0.0,
    val productDescription: String = "",
    val productImage: String = "",
    val brand: String = ""
)