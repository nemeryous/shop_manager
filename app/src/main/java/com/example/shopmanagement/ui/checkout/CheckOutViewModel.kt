package com.example.shopmanagement.ui.checkout

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.OrderRepository
import com.example.shopmanagement.data.ShippingAddressRepository
import com.example.shopmanagement.extension.toItem
import com.example.shopmanagement.model.Cart
import com.example.shopmanagement.model.CartItem
import com.example.shopmanagement.model.Item
import com.example.shopmanagement.model.Order
import com.example.shopmanagement.model.ShippingAddress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CheckOutViewModel(
    savedStateHandle: SavedStateHandle,
    val shippingAddressRepository: ShippingAddressRepository,
    val orderRepository: OrderRepository
) : ViewModel() {

    private val TAG = CheckOutViewModel::class.simpleName
    val cart = Cart.listProduct
    private val _uiState = MutableStateFlow(CheckOutUiState())
    val uiState = _uiState.asStateFlow()

    val addressId =
        checkNotNull(savedStateHandle[CheckOutDestination.addressIdArgs]).toString()


    init {
        viewModelScope.launch {
            getSelectedAddress()
        }
    }

    fun calculateTotalPrice(): Double {
        return Cart.totalPrice
    }


    fun getListProduct(): List<CartItem> {
        return Cart.getListProduct()
    }

    suspend fun getSelectedAddress() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    selected = shippingAddressRepository.getUserShippingAddressById(
                        addressId
                    ).stateIn(viewModelScope).value
                )
            }
        }.join()
    }

    suspend fun order() {
        viewModelScope.launch {
            val cartList = Cart.getListProduct()
            val shippingAddress = _uiState.value.selected
            val productList = mutableListOf<Item>()
            cartList.forEach { it->
                productList.add(it.toItem())
            }
//            Log.d(TAG, cartList.toString())
//            Log.d(TAG, productList.toString())
            val newOrder = Order(
                cartItem = productList,
                shippingAddress = shippingAddress,
                totalPrice = Cart.totalPrice
            )
            Log.d(TAG, newOrder.toString())
            orderRepository.saveOrder(newOrder)

        }.join()
        Cart.clearCart()
    }

    fun closePopup() {
        _uiState.update { it.copy(checkedOut = false) }
    }

    fun openPopup() {
        _uiState.update { it.copy(checkedOut = true) }
    }

}

data class CheckOutUiState(
    val userShippingAddress: List<ShippingAddress> = emptyList(),
    val selected: ShippingAddress = ShippingAddress(),
    val checkedOut: Boolean = false
)