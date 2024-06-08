package com.example.shopmanagement.ui.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ImageRepository
import com.example.shopmanagement.data.ProductRatingRepository
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
    private val imageRepository: ImageRepository,
    private val productRatingRepository: ProductRatingRepository
) : ViewModel() {

    private val TAG = ProductDetailsViewModel::class.simpleName

    private val productId =
        checkNotNull(savedStateHandle[ProductDetailDestination.productIdArg]).toString()

    private val _productDetailsUiState = MutableStateFlow(ProductDetailsUiState())

    val productDetailsUiState = _productDetailsUiState.asStateFlow()

    init {

        viewModelScope.launch {
            _productDetailsUiState.update { it.copy(product = getProduct()) }
            getUserRatingList()
        }

        _productDetailsUiState.value.userRating.userRatingList.forEach {pair ->
            if (pair.first == productId) {
                _productDetailsUiState.update { it.copy(rating = pair.second ) }
            }
        }

    }

    private suspend fun getProduct(): Product {
        return productRepository.fetchProduct(productId).stateIn(viewModelScope).value
    }

    fun addToCart() {
        val cartItem = CartItem(
            product = _productDetailsUiState.value.product,
            quantity = MutableStateFlow(_productDetailsUiState.value.productQuantity),
            productId = productId,
            size = _productDetailsUiState.value.productSize
        )
        openPopup()
        Cart.addProduct(cartItem)
    }

    private suspend fun getUserRatingList() {
        _productDetailsUiState.update {
            it.copy(
                userRating = productRatingRepository.getUserProductRating()
                    .stateIn(viewModelScope).value
            )
        }
    }

    suspend fun onRatingChange(rating: Double) {
        _productDetailsUiState.update { it.copy(rating = rating) }

        _productDetailsUiState.value.userRating.userRatingList.forEach { it ->
            if (it.first == productId) {
                it.copy(second = rating)
            }
        }
        viewModelScope.launch {
            productRatingRepository.updateUserProductRating(_productDetailsUiState.value.userRating)

            productRepository
                .updateProductById(
                    productId,
                    product = _productDetailsUiState.value.product.copy(
                        reviews = _productDetailsUiState.value.product.reviews + 1,
                        rating = (_productDetailsUiState.value.product.rating + _productDetailsUiState.value.rating) / 2
                    )
                )
        }.join()

    }

    fun onProductSizeChange(size: Int) {
        _productDetailsUiState.update { it.copy(productSize = size) }
    }

    fun increaseProductQuantity() {
        _productDetailsUiState.update { it.copy(productQuantity = _productDetailsUiState.value.productQuantity + 1) }
    }

    fun decreaseProductQuantity() {
        _productDetailsUiState.update { it.copy(productQuantity = _productDetailsUiState.value.productQuantity - 1) }
    }

    fun openPopup() {
        _productDetailsUiState.update { it.copy(popUpScreen = true) }
    }

    fun closePopup() {
        _productDetailsUiState.update { it.copy(popUpScreen = false) }
    }
}