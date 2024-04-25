package com.example.shopmanagement.ui.admin

import com.example.shopmanagement.model.Brand

data class ProductAddUiState(
    val productName: String = "",
    val productQuantity: String = "",
    val productPrice: String = "",
    val productDescription: String = "",
    val productImage: String = "",

    val selectedBrand: String = "",
    val brandList: List<Brand> = emptyList()
    )
