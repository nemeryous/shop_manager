package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.BrandRepository
import com.example.shopmanagement.model.Brand
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class BrandRepositoryImpl(
    private val fireStoreDb: FirebaseFirestore
) : BrandRepository {
    private val TAG = BrandRepositoryImpl::class.simpleName
    override suspend fun addBrand(brand: Brand) {
        // Add brand to database
        val dbBrand: CollectionReference = fireStoreDb.collection("brands")

        val brands = Brand(
            brand.brandName,
            brand.brandDescription,
            brand.brandImageUrl
        )

        dbBrand.add(brands)
            .addOnSuccessListener {
                Log.d(TAG, "Brand added successfully")
            }
            .addOnFailureListener { e ->
                Log.d(TAG, e.toString())
            }
    }
}