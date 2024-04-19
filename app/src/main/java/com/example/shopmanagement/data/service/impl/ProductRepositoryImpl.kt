package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Product
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class ProductRepositoryImpl(private  val fireStoreDb: FirebaseFirestore) : ProductRepository {
    private val TAG = ProductRepositoryImpl::class.simpleName
    override fun addProduct(product: Product) {
        val dbUser: CollectionReference = fireStoreDb.collection("Products")

        val products = Product(product.productName, product.productQuantity)

        dbUser.add(products)
            .addOnSuccessListener {
                Log.d(TAG, "Product added successfully")
            }
            .addOnFailureListener{
                e ->
                Log.d(TAG, e.toString())
            }
    }
}