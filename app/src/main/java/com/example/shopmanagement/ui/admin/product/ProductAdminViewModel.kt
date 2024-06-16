package com.example.shopmanagement.ui.admin.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductAdminViewModel(
    val productRepository: ProductRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductAdminUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(productList = getAllProduct()) }
        }
    }

    private suspend fun getAllProduct(): Map<String, Product> {
        return productRepository.fetchAllProducts().stateIn(viewModelScope).value
    }


    fun removeProduct(productId: String) {
        viewModelScope.launch {
            productRepository.deleteProductById(productId)
            _uiState.update { it.copy(productList = getAllProduct())
            }
       }
    }
}

data class ProductAdminUiState(
    val productList: Map<String, Product> = emptyMap()
)