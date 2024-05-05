package com.example.shopmanagement.ui.home

import com.example.shopmanagement.model.Brand
import com.example.shopmanagement.model.Product

data class HomeScreenUiState(
    val productList: Map<String,Product> = emptyMap(),
    val brandList: List<Brand> = emptyList()
)
