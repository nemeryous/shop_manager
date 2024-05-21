package com.example.shopmanagement.ui.checkout

import androidx.lifecycle.ViewModel
import com.example.shopmanagement.model.Cart
import com.example.shopmanagement.model.CartItem

class CheckOutViewModel : ViewModel() {

    val cart = Cart.listProduct

    fun addProduct(item: CartItem) {
        Cart.addProduct(item)
    }

    fun calculateTotalPrice(): Double {
        return Cart.totalPrice
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