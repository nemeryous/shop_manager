package com.example.shopmanagement.data.service.impl

import com.example.shopmanagement.data.OrderRepository
import com.example.shopmanagement.model.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class OrderRepositoryImpl(
    val auth: FirebaseAuth,
    val firestoreDb: FirebaseFirestore
) : OrderRepository {

    val orderDb = firestoreDb.collection("orders")
    override suspend fun saveOrder(order: Order) {
        val newOrderRef = orderDb.document()
        order.orderId = newOrderRef.id
        order.userId = auth.currentUser?.uid ?: "0"

        newOrderRef.set(order)
            .addOnSuccessListener {

            }
            .addOnFailureListener {

            }
            .await()
    }

    override suspend fun getUserOrder(): Flow<List<Order>> = callbackFlow {
        val orderList = mutableListOf(Order())
        orderDb.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    orderList.add(document.toObject(Order::class.java))
                }
                trySend(orderList)
            }
            .addOnFailureListener {

            }

        awaitClose { close() }
    }
}