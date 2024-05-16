package com.example.shopmanagement.model

import kotlinx.coroutines.flow.MutableStateFlow

data class CartItem(
    val productId: String,
    val product: Product,
    var quantity: MutableStateFlow<Int>
)



