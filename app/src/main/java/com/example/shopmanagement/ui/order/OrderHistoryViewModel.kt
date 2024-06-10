package com.example.shopmanagement.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.OrderRepository
import com.example.shopmanagement.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderHistoryViewModel(
    val orderRepository: OrderRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(userOrderList = fetchUserOrder()) }
        }
    }

    private suspend fun fetchUserOrder(): List<Order> {
        return orderRepository.getUserOrder().stateIn(viewModelScope).value
    }

}

data class OrderHistoryUiState(
    val userOrderList: List<Order> = emptyList()
)