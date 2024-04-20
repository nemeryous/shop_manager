package com.example.shopmanagement.model

import com.example.shopmanagement.R


data class CartItem(
    val id: Int,
    val name: String,
    val price: Double,
    var quantity: Int,
    val imageResourceId: Int
)

val cartItems = listOf(
    CartItem(1, "Nike Air", 66.3, 2, R.drawable.h2),
    CartItem(2, "Adidas", 66.3, 2, R.drawable.h3),
    CartItem(3, "Puma", 66.3, 2, R.drawable.h4),
    CartItem(4, "Asics Gel", 66.3, 2, R.drawable.h5),
    CartItem(5, "Reebok", 66.3, 2, R.drawable.h6),
    CartItem(6, "New Balance", 66.3, 2, R.drawable.h4),
    CartItem(7, "Converse", 66.3, 2, R.drawable.h2)
)

