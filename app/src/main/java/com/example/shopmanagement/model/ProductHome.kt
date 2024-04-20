package com.example.shopmanagement.model

import androidx.annotation.DrawableRes

data class ProductHome(
    val id: String,
    val name: String,
    val price: Float,
    @DrawableRes val imageRes: Int
)
