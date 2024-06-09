package com.example.shopmanagement.data

import com.example.shopmanagement.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun addProduct(product: Product)

    suspend fun fetchAllProducts(): Flow<Map<String,Product>>

    suspend fun fetchProduct(productId: String): Flow<Product>

    fun deleteProductById(productId: String)

    fun updateProductById(productId: String, product: Product)

    fun findProductByBrand(brandName: String): Flow<Map<String,Product>>

    suspend fun findProductByName(productName: String): Flow<Map<String, Product>>
}