package com.example.shopmanagement.model

import com.example.shopmanagement.R

data class CheckOutItem(
     val id: Int,
     val name: String,
     val price: Double,
     var quantity: Int,
     val imageResourceId: Int
 )
val cartItems = listOf(
    CheckOutItem(1, "Nike Air", 66.3, 2, R.drawable.h2),
    CheckOutItem(2, "Adidas", 66.3, 2, R.drawable.h3),
    CheckOutItem(3, "Puma", 66.3, 2, R.drawable.h4),
    CheckOutItem(4, "Asics Gel", 66.3, 2, R.drawable.h5),
    CheckOutItem(5, "Reebok", 66.3, 2, R.drawable.h6),
    CheckOutItem(6, "New Balance", 66.3, 2, R.drawable.h4),
    CheckOutItem(7, "Converse", 66.3, 2, R.drawable.h2)
)