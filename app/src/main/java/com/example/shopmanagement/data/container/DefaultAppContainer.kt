package com.example.shopmanagement.data.container

import android.content.Context
import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.data.service.impl.AuthRepositoryImpl
import com.example.shopmanagement.data.service.impl.ProductRepositoryImpl
import com.example.shopmanagement.data.service.module.FirebaseModule


class DefaultAppContainer(private val context: Context) : AppContainer {
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(FirebaseModule.auth())
    }
    override val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(FirebaseModule.firestore())
    }
}