package com.example.shopmanagement.data.service.impl

import android.util.Log
import com.example.shopmanagement.data.ProductRatingRepository
import com.example.shopmanagement.model.UserRating
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ProductRatingRepoImpl(
    private val fireStoreDb: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ProductRatingRepository {

    val dbRating = fireStoreDb.collection("user_ratings")
    val currentUserUid = auth.currentUser?.uid
    override suspend fun getUserProductRating(): Flow<UserRating> = callbackFlow {
        if (currentUserUid != null) {
            dbRating.document(currentUserUid).get().addOnSuccessListener { result ->

                if (result != null) {
                    if (result.toObject(UserRating::class.java) != null) {
                        trySend(result.toObject(UserRating::class.java)!!)
                    }
                    trySend(UserRating())
                    Log.d("ProductRatingRepoImpl", "abc")
                }
//                Log.d("ProductRatingRepoImpl", result.toString())
            }
                .addOnFailureListener {
                    trySend(UserRating())
                }
        }

        awaitClose { cancel() }
    }

    override suspend fun updateUserProductRating(userRating: UserRating) {
        if (currentUserUid != null) {
            dbRating.document(currentUserUid).set(userRating)
        }
    }
}