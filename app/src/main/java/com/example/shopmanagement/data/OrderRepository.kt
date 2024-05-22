package com.example.shopmanagement.data

import com.example.shopmanagement.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    suspend fun saveOrder(order: Order)

    suspend fun getUserOrder(): Flow<List<Order>>

    suspend fun fetchAllOrders(): Flow<List<Order>>

}