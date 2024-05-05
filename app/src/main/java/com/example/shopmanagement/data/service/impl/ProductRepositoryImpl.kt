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


        dbProduct.add(products).addOnSuccessListener {
                Log.d(TAG, "Product added successfully")
            }.addOnFailureListener { e ->
                Log.d(TAG, e.toString())
            }
    }

    override suspend fun fetchAllProducts(): Flow<Map<String, Product>> = callbackFlow {

        dbProduct.get().addOnSuccessListener { result ->
            val productList = mutableMapOf<String, Product>()
            for (document in result) {
                Log.d(TAG, document.id)
                val product = Product(
                    productName = document.data["productName"].toString(),
                    productDescription = document.data["productDescription"].toString(),
                    productImage = document.data["productImage"].toString(),
                    productQuantity = 0,
                    productPrice = document.data["productPrice"].toString().toDoubleOrNull() ?: 0.0,
                    brand = document.data["brand"].toString()
                )

                productList[document.id] = product
            }

            trySend(productList)
        }

        awaitClose { close() }

    }

    override suspend fun fetchProduct(productId: String): Flow<Product> = callbackFlow {

        dbProduct.document(productId).get().addOnSuccessListener {

            if (it != null) {
                val product = Product(
                    productName = it.data!!["productName"]?.toString() ?: "",
                    productQuantity = it.data!!["productQuantity"].toString().toIntOrNull() ?: 0,
                    productImage = it.data!!["productImage"].toString(),
                    productPrice = it.data!!["productPrice"].toString().toDoubleOrNull() ?: 0.0,
                    productDescription = it.data!!["productDescription"].toString(),
                    brand = it.data!!["brand"].toString()
                )
                trySend(product)
            }


        }


        awaitClose { close() }
    }

}