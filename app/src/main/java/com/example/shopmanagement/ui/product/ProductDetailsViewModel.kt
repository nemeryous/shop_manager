package com.example.shopmanagement.ui.product

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Product
import kotlinx.coroutines.flow.stateIn

class ProductDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val productId =
        checkNotNull(savedStateHandle[ProductDetailDestination.productIdArg]).toString()


    suspend fun getProduct() {
        Log.d(ProductDetailsViewModel::class.simpleName, productId)
        Log.d(ProductDetailsViewModel::class.simpleName, "abc")
        val product: Product = productRepository.fetchProduct(productId).stateIn(viewModelScope).value
        Log.d(ProductDetailsViewModel::class.simpleName, "abc")
    }
}