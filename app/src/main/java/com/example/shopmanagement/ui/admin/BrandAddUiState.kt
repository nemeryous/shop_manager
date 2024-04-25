package com.example.shopmanagement.ui.admin

import com.example.shopmanagement.model.Brand

data class BrandAddUiState(
    val brandName: String = "",
    val brandDescription: String = "",
    val brandImageUrl: String = "",

    val brandList: List<Brand> = emptyList()
)
