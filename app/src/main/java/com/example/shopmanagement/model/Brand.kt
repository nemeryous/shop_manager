package com.example.shopmanagement.model

data class Brand(
    val brandId: Int = 0,
    val brandName: String = "",
)

object Brands {
    val brands = listOf(
        Brand(1,"Nike"),
        Brand(2,"Nike"),
        Brand(3,"Nike"),
        Brand(4,"Nike"),
        Brand(5,"Nike"),
    )
}