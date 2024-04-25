package com.example.shopmanagement.ui.home

import com.example.shopmanagement.model.Product

data class HomeScreenUiState(
    val productList: List<Product> = emptyList()
)
