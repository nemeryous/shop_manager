package com.example.shopmanagement.data.container

import android.content.Context

import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.data.BrandRepository
import com.example.shopmanagement.data.ImageRepository
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.data.service.impl.AuthRepositoryImpl
import com.example.shopmanagement.data.service.impl.BrandRepositoryImpl
import com.example.shopmanagement.data.service.impl.ImageRepositoryImpl
import com.example.shopmanagement.data.service.impl.ProductRepositoryImpl
import com.example.shopmanagement.data.service.module.FirebaseModule


class DefaultAppContainer(private val context: Context) : AppContainer {
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(FirebaseModule.auth())
    }
    override val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(FirebaseModule.firestore())
    }
    override val imageRepository: ImageRepository by lazy {
        ImageRepositoryImpl(FirebaseModule.storage())
    }
    override val brandRepository: BrandRepository by lazy {
        BrandRepositoryImpl(FirebaseModule.firestore())
    }
}