package com.example.shopmanagement.data.service.impl

import com.example.shopmanagement.data.ShippingAddressRepository
import com.example.shopmanagement.model.ShippingAddress
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class ShippingAddressRepositoryImpl(val auth: FirebaseAuth, val firestoreDb: FirebaseFirestore) :
    ShippingAddressRepository {
    val dbShippingAddress = firestoreDb.collection("shipping_address")
    override suspend fun saveShippingAddress(address: ShippingAddress) {
        val newShippingAddressRef = dbShippingAddress.document()
        address.id = newShippingAddressRef.id
        address.userId = auth?.currentUser?.uid ?: ""

        newShippingAddressRef.set(address).addOnSuccessListener {

        }.addOnFailureListener {

            }.await()

    }

    override suspend fun getUserShippingAddress(): Flow<List<ShippingAddress>> = callbackFlow {
        val shippingAddressList = mutableListOf<ShippingAddress>()

        dbShippingAddress.whereEqualTo("userId", auth.currentUser?.uid).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val shippingAddress = document.toObject(ShippingAddress::class.java)
                    shippingAddressList.add(shippingAddress)
                }

                trySend(shippingAddressList)
            }.addOnFailureListener {

            }

        awaitClose { close() }
    }

    override suspend fun getUserShippingAddressById(addressId: String): Flow<ShippingAddress> =
        callbackFlow {
            var shippingAddress = ShippingAddress()
            dbShippingAddress.document(addressId).get().addOnSuccessListener { document ->
                if (document != null) {
                    shippingAddress = document.toObject(ShippingAddress::class.java)!!
                    trySend(shippingAddress)
                }
            }.addOnFailureListener {

                }

            awaitClose { close() }


        }
}