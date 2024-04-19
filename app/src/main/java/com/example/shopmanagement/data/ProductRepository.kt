package com.example.shopmanagement.data

import com.example.shopmanagement.model.Product

interface ProductRepository {

    fun addProduct(product: Product)

}