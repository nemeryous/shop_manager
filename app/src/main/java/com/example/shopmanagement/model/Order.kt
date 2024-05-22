package com.example.shopmanagement.model

data class Order(
    var orderId: String = "",
    val cartItem: List<CartItem> = emptyList(),
    val shippingAddress: ShippingAddress = ShippingAddress(),
    var userId: String = ""
)
