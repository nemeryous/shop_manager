package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.CategoryRepository
import com.example.shopmanagement.model.Category
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl (private val firestore: FirebaseFirestore) : CategoryRepository {

    private val TAG = CategoryRepositoryImpl::class.simpleName
    override suspend fun addCategory(category: Category) {
        val dbCategory = firestore.collection("categories")

        dbCategory.add(category).addOnSuccessListener {
            Log.d(TAG, "Add category success, category id : ${it.id}")
        }
            .addOnFailureListener {
                Log.d(TAG, "Add category failed")
            }
    }

    override suspend fun fetchAllCategory(): Flow<List<Category>> {
        TODO("Not yet implemented")
    }
}