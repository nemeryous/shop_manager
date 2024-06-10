package com.example.shopmanagement.ui.order

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ImageRepository
import com.example.shopmanagement.data.OrderRepository
import com.example.shopmanagement.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderDetailsViewModel(
    private val orderRepository: OrderRepository,
    private val savedStateHandle: SavedStateHandle,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val orderId =
        checkNotNull(savedStateHandle[OrderDetailsDestination.orderIdArgs]).toString()
    private val _uiState = MutableStateFlow(OrderDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(order = getOrderDetails()) }
        }
    }

    private suspend fun getOrderDetails(): Order {
        return orderRepository.getOrderByOrderId(orderId).stateIn(viewModelScope).value
    }

}

data class OrderDetailsUiState(
    val order: Order = Order()
)