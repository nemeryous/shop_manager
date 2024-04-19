package com.example.shopmanagement.ui.product

import androidx.lifecycle.ViewModel
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val _productUiState = MutableStateFlow(ProductUiState())

    val productUiState: StateFlow<ProductUiState> = _productUiState.asStateFlow()

    fun updateProductName(name: String) {
        _productUiState.update { it.copy(productName = name) }
    }

    fun updateProductQuantity(qtyString: String) {
        val qty = qtyString.toIntOrNull() ?: 0
        _productUiState.update { it.copy(productQuantity = qty) }
    }

    fun addProduct(product: Product) {
        productRepository.addProduct(product)
    }

}