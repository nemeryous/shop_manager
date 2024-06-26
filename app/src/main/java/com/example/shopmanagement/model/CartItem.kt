package com.example.shopmanagement.model

import kotlinx.coroutines.flow.MutableStateFlow

data class CartItem(
    val productId: String = "",
    val product: Product = Product(),
    var quantity: MutableStateFlow<Int> = MutableStateFlow(1),
    val size: Int = 40
)

data class Item(
    val productId: String = "",
    val product: Product = Product(),
    val quantity: Int = 1,
    val size: Int = 40
)



