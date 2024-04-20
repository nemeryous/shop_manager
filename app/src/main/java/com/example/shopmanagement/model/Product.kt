package com.example.shopmanagement.model

data class Product(
    val productName: String,
    val productQuantity: Int,

) {
    constructor(): this("", 0)
}
