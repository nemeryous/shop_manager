package com.example.shopmanagement.ui.admin

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ImageRepository
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.extension.toProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductAddViewModel(
    private val productRepository: ProductRepository, private val imageRepository: ImageRepository
) : ViewModel() {

    private val _productAddUiState = MutableStateFlow(ProductAddUiState())

    val productAddUiState: StateFlow<ProductAddUiState> = _productAddUiState.asStateFlow()

    var isChooseImage by mutableStateOf(false)

    var expanded by mutableStateOf(false)


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


    fun onExpanded() {
        expanded = !expanded
    }

    fun updateSelectedCategory(selected: String) {
        _productAddUiState.update { it.copy(selectedCategory = selected) }
    }

    fun onClickAddImage() {
        isChooseImage = !isChooseImage
    }

    suspend fun addProduct(bitmap: Bitmap) {
        var imageUrl = ""
        viewModelScope.launch {
            imageUrl = imageRepository.addImage(bitmap)
        }.join()
        Log.d(ProductAddViewModel::class.simpleName, imageUrl)

        _productAddUiState.update { it.copy(productImage = imageUrl) }
        productRepository.addProduct(product = productAddUiState.value.toProduct())

    }

}