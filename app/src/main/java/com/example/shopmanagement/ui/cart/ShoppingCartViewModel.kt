package com.example.shopmanagement.ui.cart

import androidx.lifecycle.ViewModel
import com.example.shopmanagement.model.Cart
import com.example.shopmanagement.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShoppingCartViewModel: ViewModel() {

    val listProduct = Cart.listProduct

    fun addProduct(item: CartItem) {
        Cart.addProduct(item)
    }

    fun removeProduct(item: CartItem) {
        Cart.removeProduct(item)
    }

    fun getListProduct(): List<CartItem> {
        return Cart.getListProduct()
    }


    fun increaseQuantity(item: CartItem) {
        Cart.increaseQuantity(item)
    }

    fun decreaseQuantity(item: CartItem) {
        Cart.decreaseQuantity(item)
    }
}