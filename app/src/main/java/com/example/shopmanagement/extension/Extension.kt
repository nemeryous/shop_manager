package com.example.shopmanagement.extension

import com.example.shopmanagement.model.Brand
import com.example.shopmanagement.model.Category
import com.example.shopmanagement.model.Product
import com.example.shopmanagement.ui.CategoryAddUiState
import com.example.shopmanagement.ui.admin.BrandAddUiState
import com.example.shopmanagement.ui.admin.ProductAddUiState

fun ProductAddUiState.toProduct(): Product = Product(
    productName = productName,
    productQuantity = productQuantity.toIntOrNull()?:0,
    productPrice = productPrice.toDoubleOrNull()?:0.0,
    productDescription = productDescription,
    productImage = productImage,
    brand = selectedBrand
)

fun BrandAddUiState.toBrand(): Brand = Brand(
    brandName = brandName,
    brandDescription = brandDescription,
    brandImageUrl = brandImageUrl
)

fun CategoryAddUiState.toCategory(): Category = Category(
    categoryName = categoryName,
    categoryDesc = categoryDesc
)