package com.example.shopmanagement.ui.checkout

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ShippingAddressRepository
import com.example.shopmanagement.model.Cart
import com.example.shopmanagement.model.CartItem
import com.example.shopmanagement.model.ShippingAddress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckOutViewModel(
    val shippingAddressRepository: ShippingAddressRepository
) : ViewModel() {

    val cart = Cart.listProduct
    private val _userShippingAddress = MutableStateFlow<List<ShippingAddress>>(emptyList())
    val userShippingAddress = _userShippingAddress.asStateFlow()

    val _selectedAddress = MutableStateFlow(ShippingAddress())
    val selectedAddress = _selectedAddress.asStateFlow()
    init {
        getUserAddress()
        if (_userShippingAddress.value.isNotEmpty()) {
            Log.d("CheckOutViewModel", _userShippingAddress.value.first().toString())
            _selectedAddress.value = _userShippingAddress.value.first()
        }

    }

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

    fun getUserAddress() {
        viewModelScope.launch {
            _userShippingAddress.value = shippingAddressRepository.getUserShippingAddress()
        }
    }

    fun updateSelectedAddress(selected: ShippingAddress) {
        _selectedAddress.value = selected
    }

}