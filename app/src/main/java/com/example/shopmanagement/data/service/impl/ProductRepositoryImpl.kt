package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Product
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ProductRepositoryImpl(
    private val fireStoreDb: FirebaseFirestore,
) : ProductRepository {
    private val TAG = ProductRepositoryImpl::class.simpleName
    private val dbProduct: CollectionReference = fireStoreDb.collection("Products")
    override fun addProduct(product: Product) {


        val products = Product(
            product.productName,
            product.productQuantity,
            product.productPrice,
            product.productDescription,
            product.productImage,
            product.brand
        )


        dbProduct.add(products)
            .addOnSuccessListener {
                Log.d(TAG, "Product added successfully")
            }
            .addOnFailureListener { e ->
                Log.d(TAG, e.toString())
            }
    }

    override suspend fun fetchAllProduct(): Flow<List<Product>>  = callbackFlow {

        dbProduct.get().addOnSuccessListener {result ->
            val productList = mutableListOf<Product>()
            for (document in result) {
                val product = Product (
                    productName = document.data["productName"].toString(),
                    productDescription = document.data["productDescription"].toString(),
                    productImage = document.data["productImage"].toString(),
                    productQuantity = 0,
                    productPrice = document.data["productPrice"].toString().toDoubleOrNull()?:0.0,
                    brand = document.data["brand"].toString()
                )

                productList.add(product)
            }

            trySend(productList)
        }

        awaitClose { close() }

    }
}