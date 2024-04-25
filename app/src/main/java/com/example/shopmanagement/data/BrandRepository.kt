package com.example.shopmanagement.data

import com.example.shopmanagement.model.Brand
import kotlinx.coroutines.flow.Flow

interface BrandRepository {

    suspend fun addBrand(brand: Brand)

    suspend fun fetchAllBrand(): Flow<List<Brand>>


}