package com.example.shopmanagement.ui.product

import com.example.shopmanagement.model.Product
import com.example.shopmanagement.model.UserRating

data class ProductDetailsUiState(
    val product: Product = Product(),
    val rating: Double = 0.0,
    val userRating: UserRating = UserRating(emptyList()),
    val productSize: Int = 40,
    val productQuantity: Int = 1,
    val popUpScreen: Boolean = false
)
