package com.example.shopmanagement.data

import com.example.shopmanagement.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun addProduct(product: Product)

    suspend fun fetchAllProduct(): Flow<List<Product>>

}