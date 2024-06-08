package com.example.shopmanagement.model

data class UserRating(
    val userRatingList : List<Pair<String, Double>> = emptyList() // Pair<ProductId, UserRating>
)
