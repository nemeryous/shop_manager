package com.example.shopmanagement.data.container

import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.data.ProductRepository

interface AppContainer {
    val authRepository: AuthRepository
    val productRepository: ProductRepository

}
