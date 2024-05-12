package com.example.shopmanagement.ui.product

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopmanagement.data.ImageRepository
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Cart
import com.example.shopmanagement.model.CartItem
import com.example.shopmanagement.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository,
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val TAG = ProductDetailsViewModel::class.simpleName

    private val productId =
        checkNotNull(savedStateHandle[ProductDetailDestination.productIdArg]).toString()

    private val _productDetailsUiState = MutableStateFlow(ProductDetailsUiState())

    val productDetailsUiState = _productDetailsUiState.asStateFlow()

    init {
        viewModelScope.launch {
            _productDetailsUiState.update { it.copy(product = getProduct()) }
        }

    }

    private suspend fun getProduct(): Product {
        return productRepository.fetchProduct(productId).stateIn(viewModelScope).value
    }

    fun addToCart() {
        val cartItem = CartItem(
            product = _productDetailsUiState.value.product,
            quantity = MutableStateFlow(1),
            productId = productId
        )

        Cart.addProduct(cartItem)
    }
}