package com.example.shopmanagement.data

import com.example.shopmanagement.model.ShippingAddress

interface ShippingAddressRepository {

    suspend fun saveShippingAddress(address: ShippingAddress)

    suspend fun getUserShippingAddress(): List<ShippingAddress>

}