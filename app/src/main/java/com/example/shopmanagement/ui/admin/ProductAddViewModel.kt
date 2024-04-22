package com.example.shopmanagement.ui.admin

import androidx.lifecycle.ViewModel
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductAddViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val _productAddUiState = MutableStateFlow(ProductAddUiState())

    val productAddUiState: StateFlow<ProductAddUiState> = _productAddUiState.asStateFlow()

    fun updateProductName(name: String) {
        _productAddUiState.update { it.copy(productName = name) }
    }

    fun updateProductQuantity(qtyString: String) {
        val qty = qtyString.toIntOrNull() ?: 0
        _productAddUiState.update { it.copy(productQuantity = qty) }
    }

    fun addProduct(product: Product) {
        productRepository.addProduct(product)
    }

}