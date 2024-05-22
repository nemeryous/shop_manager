package com.example.shopmanagement.model

data class Order(
    var orderId: String = "",
    val cartItem: List<Item> = emptyList(),
    val shippingAddress: ShippingAddress = ShippingAddress(),
    var userId: String = "",
    val status: String = "Chưa xác nhận",
    val totalPrice: Double = 0.0
)
