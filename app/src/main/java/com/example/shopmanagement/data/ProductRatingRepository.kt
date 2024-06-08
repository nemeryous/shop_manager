package com.example.shopmanagement.data

import com.example.shopmanagement.model.UserRating
import kotlinx.coroutines.flow.Flow

interface ProductRatingRepository {

    suspend fun getUserProductRating(): Flow<UserRating>

    suspend fun updateUserProductRating(userRating : UserRating)

}