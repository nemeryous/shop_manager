package com.example.shopmanagement.data

import com.example.shopmanagement.model.Brand

interface BrandRepository {

    suspend fun addBrand(brand: Brand)


}