package com.example.shopmanagement.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Cart {

    private val _listProducts = MutableStateFlow<List<CartItem>>(emptyList())
    val listProduct = _listProducts.asStateFlow()

    var totalPrice by mutableStateOf(0.0)
        private set

    fun addProduct(item: CartItem) {
        val foundItem = _listProducts.value.toMutableList().find {
            it.productId == item.productId
        }
        if(foundItem != null) {
            foundItem?.let {
                it.quantity.value++
            }
        } else
        {
            _listProducts.value += item
        }

        calculateTotalPrice()
    }

    fun removeProduct(item: CartItem) {
        _listProducts.value -= item
        calculateTotalPrice()
    }

    fun getListProduct(): List<CartItem> {
        return _listProducts.value.toList()
    }

    fun calculateTotalPrice() {
        totalPrice = 0.0
        for (item in _listProducts.value) {
            totalPrice += item.product.productPrice * item.quantity.value
        }


    }

    fun increaseQuantity(item: CartItem) {
        val foundItem = _listProducts.value.toMutableList().find {
            it.productId == item.productId
        }
        foundItem?.let {
            it.quantity.value++
        }

        calculateTotalPrice()
    }

    fun decreaseQuantity(item: CartItem) {
        val foundItem = _listProducts.value.toMutableList().find {
            it.productId == item.productId
        }
        foundItem?.let {
            it.quantity.value--
        }

        calculateTotalPrice()
    }

}