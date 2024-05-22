package com.example.shopmanagement.ui.admin.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.data.OrderRepository
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.data.ShippingAddressRepository
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
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderAdminUiState())
    val uiState = _uiState.asStateFlow()

    init {

    }
    private suspend fun fetchAllData() {
        viewModelScope.launch {
            val fetchOrder = async { _uiState.update { it.copy(orderList = orderRepository.fetchAllOrders().stateIn(viewModelScope).value)  } }
            fetchOrder.await()
        }

    }

    suspend fun fetchUser(userId: String): String {
        val user =  authRepository.getUser(userId).stateIn(viewModelScope).value
        return "${user.firstName} ${user.lastName}"
    }
}

data class OrderAdminUiState(
    val orderList: List<Order> = emptyList()
)