package com.example.shopmanagement.ui.admin.order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.data.OrderRepository
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Order
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderAdminViewModel(
    val orderRepository: OrderRepository,
    val authRepository: AuthRepository,
    val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderAdminUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchAllData()
        }

    }

    private suspend fun fetchAllData() {
        viewModelScope.launch {
            val fetchOrder = async {
                _uiState.update {
                    it.copy(
                        orderList = orderRepository.fetchAllOrders().stateIn(viewModelScope).value
                    )
                }
            }
            fetchOrder.await()
        }
    }

    suspend fun fetchUser(userId: String): String {
        val user = authRepository.getUser(userId).stateIn(viewModelScope).value
        return "${user.firstName} ${user.lastName}"
    }

    fun updateStatus(orderId: String) {
        viewModelScope.launch {
            orderRepository.updateStatus(orderId)
            uiState.value.orderList.forEach {order ->
                order.cartItem.forEach {item ->
                    val product = item.product.copy(productQuantity = item.product.productQuantity - item.quantity, sold = item.quantity)
                    productRepository.updateProductById(item.productId, product)
                    Log.d("OrderAdminViewModel", "abc")
                }
            }
            fetchAllData()
        }

    }
}

data class OrderAdminUiState(
    val orderList: List<Order> = emptyList()
)