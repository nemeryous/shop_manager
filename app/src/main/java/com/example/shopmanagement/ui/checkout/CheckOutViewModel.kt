package com.example.shopmanagement.ui.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ShippingAddressRepository
import com.example.shopmanagement.model.Cart
import com.example.shopmanagement.model.CartItem
import com.example.shopmanagement.model.ShippingAddress
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckOutViewModel(
    val shippingAddressRepository: ShippingAddressRepository
) : ViewModel() {

    private val TAG = CheckOutViewModel::class.simpleName
    val cart = Cart.listProduct
    private val _uiState = MutableStateFlow(CheckOutUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            _uiState.update { it.copy(userShippingAddress = getUserAddress()) }
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

    suspend fun getUserAddress(): List<ShippingAddress> {
        return shippingAddressRepository.getUserShippingAddress()
    }

    fun updateSelectedAddress(selected: ShippingAddress) {
//        _selectedAddress.value = selected
    }

    fun getSelectedAddress() {
//        _selectedAddress.value = _userShippingAddress.value.first()
        _uiState.update { it.copy(selected = _uiState.value.userShippingAddress.first()) }
    }

}

data class CheckOutUiState(
    val userShippingAddress: List<ShippingAddress> = emptyList(),
    val selected: ShippingAddress = ShippingAddress()
)