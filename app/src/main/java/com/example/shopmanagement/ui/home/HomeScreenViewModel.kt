package com.example.shopmanagement.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Product
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())

    val homeScreenUiState = _homeScreenUiState.asStateFlow()


    init {
        viewModelScope.launch {
            _homeScreenUiState.update { it.copy(productList = fetchAllProduct()) }
        }

    }

    suspend fun fetchAllProduct(): List<Product> {
        return productRepository.fetchAllProduct().stateIn(viewModelScope).value
    }

}