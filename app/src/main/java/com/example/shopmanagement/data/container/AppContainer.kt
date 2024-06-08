package com.example.shopmanagement.data.container

import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.data.BrandRepository
import com.example.shopmanagement.data.CategoryRepository
import com.example.shopmanagement.data.ImageRepository
import com.example.shopmanagement.data.OrderRepository
import com.example.shopmanagement.data.ProductRatingRepository
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.data.ShippingAddressRepository

interface AppContainer {
    val authRepository: AuthRepository
    val productRepository: ProductRepository
    val imageRepository: ImageRepository
    val brandRepository: BrandRepository
    val categoryRepository: CategoryRepository
    val shippingAddressRepository: ShippingAddressRepository
    val orderRepository: OrderRepository
    val productRatingRepository: ProductRatingRepository

}
