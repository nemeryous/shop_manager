package com.example.shopmanagement.ui.admin

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var isChooseImage by mutableStateOf(false)


    fun updateProductName(name: String) {
        _productAddUiState.update { it.copy(productName = name) }
    }

    fun updateProductQuantity(qtyString: String) {
        _productAddUiState.update { it.copy(productQuantity = qtyString) }
    }

    fun updateProductPrice(productPriceString: String) {
        _productAddUiState.update { it.copy(productPrice = productPriceString) }
    }

    fun updateProductDescription(productDesc: String) {
        _productAddUiState.update { it.copy(productDescription = productDesc) }
    }

    fun addProduct(product: Product) {
        productRepository.addProduct(product)
    }

    fun onClickAddImage() {
        isChooseImage = !isChooseImage
    }

}