package com.example.shopmanagement.data

import com.example.shopmanagement.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun addCategory(category: Category)

    suspend fun fetchAllCategory(): Flow<List<Category>>

}