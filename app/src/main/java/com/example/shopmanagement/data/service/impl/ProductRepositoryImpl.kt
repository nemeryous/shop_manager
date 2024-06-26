package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Product
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
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

    // lấy hết toàn bộ sản phẩm
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
                    brand = document.data["brand"].toString(),
                    rating = document.data["rating"].toString().toDoubleOrNull() ?: 0.0,
                    reviews = document.data["reviews"].toString().toIntOrNull() ?: 1
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
                    brand = it.data!!["brand"].toString(),
                    reviews = it.data!!["reviews"].toString().toIntOrNull() ?: 1,
                    rating = it.data!!["rating"].toString().toDoubleOrNull() ?: 0.0,
                    sold = it.data!!["sold"].toString().toIntOrNull() ?: 0
                )
                trySend(product)
            }


        }

        awaitClose { close() }
    }

    override fun deleteProductById(productId: String) {
        dbProduct.document(productId).delete()
    }

    override fun updateProductById(productId: String, product: Product) {
        dbProduct.document(productId).set(product)
    }

    override fun findProductByBrand(brandName: String): Flow<Map<String, Product>> = callbackFlow {
        dbProduct.whereEqualTo("brand", brandName).get().addOnSuccessListener { result ->
            val productList = mutableMapOf<String, Product>()

            if (result != null) {
                for (document in result) {
                    val product = Product(
                        productName = document.data["productName"].toString(),
                        productDescription = document.data["productDescription"].toString(),
                        productImage = document.data["productImage"].toString(),
                        productQuantity = 0,
                        productPrice = document.data["productPrice"].toString().toDoubleOrNull()
                            ?: 0.0,
                        brand = document.data["brand"].toString(),
                        rating = document.data["rating"].toString().toDoubleOrNull() ?: 0.0,
                        reviews = document.data["reviews"].toString().toIntOrNull() ?: 1
                    )

                    productList[document.id] = product
                }
            }

            trySend(productList)
        }
            .addOnFailureListener {

            }

        awaitClose { cancel() }
    }

    override suspend fun findProductByName(productName: String): Flow<Map<String, Product>> =
        callbackFlow {
            Log.d(TAG, "abc")
            dbProduct.orderBy("productName").startAt(productName).endAt(

                "$productName\uf8ff").get()
                .addOnSuccessListener { result ->
                    val productList = mutableMapOf<String, Product>()
                    if (result != null) {
                        for (document in result) {
                            Log.d(TAG, document.toString())
                            val product = Product(
                                productName = document.data["productName"].toString(),
                                productDescription = document.data["productDescription"].toString(),
                                productImage = document.data["productImage"].toString(),
                                productQuantity = 0,
                                productPrice = document.data["productPrice"].toString()
                                    .toDoubleOrNull() ?: 0.0,
                                brand = document.data["brand"].toString(),
                                rating = document.data["rating"].toString().toDoubleOrNull() ?: 0.0,
                                reviews = document.data["reviews"].toString().toIntOrNull() ?: 1
                            )

                            productList[document.id] = product
                        }
                    }

                    trySend(productList)
                }
                .addOnFailureListener {

                }
            awaitClose { cancel() }
        }
}