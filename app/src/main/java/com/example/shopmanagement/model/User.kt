package com.example.shopmanagement.model

data class User(
    val userName: String,
    val userAge: Int,
) {
    constructor(): this ("", 0)
}
