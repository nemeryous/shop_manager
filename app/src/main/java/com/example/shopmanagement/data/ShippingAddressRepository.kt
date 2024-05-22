package com.example.shopmanagement.data

import com.example.shopmanagement.model.ShippingAddress
import kotlinx.coroutines.flow.Flow

interface ShippingAddressRepository {

    suspend fun saveShippingAddress(address: ShippingAddress)

    suspend fun getUserShippingAddress(): Flow<List<ShippingAddress>>

    suspend fun getUserShippingAddressById(addressId: String): Flow<ShippingAddress>

}