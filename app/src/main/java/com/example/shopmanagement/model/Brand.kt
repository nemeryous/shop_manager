package com.example.shopmanagement.model

data class Brand(
    val brandId: Int = 0,
    val brandName: String = "",
)

object Brands {
    val brands = listOf(
        Brand(1,"All"),
        Brand(2,"Nike"),
        Brand(3,"Adidas"),
        Brand(4,"Puma"),
        Brand(5,"Reebok"),
        Brand(5,"New Balance"),
        Brand(5,"Converse"),
    )
}