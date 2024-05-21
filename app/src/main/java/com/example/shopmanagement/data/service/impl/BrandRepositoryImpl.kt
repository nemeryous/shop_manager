package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.BrandRepository
import com.example.shopmanagement.model.Brand
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

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

    override suspend fun fetchAllBrand(): Flow<List<Brand>> = callbackFlow {
        val dbBrand = fireStoreDb.collection("brands")
        dbBrand.get()
            .addOnSuccessListener { result ->
                val brandList = mutableListOf<Brand>()
                for (document in result) {
                    val brand = Brand(
                        document.data["brandName"].toString(),
                        document.data["brandDescription"].toString(),
                        document.data["brandImageUrl"].toString()
                    )
                    brandList.add(brand)
                }
                trySend(brandList)
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

        awaitClose { close() }
    }
}