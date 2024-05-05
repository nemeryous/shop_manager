package com.example.shopmanagement.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.BrandRepository
import com.example.shopmanagement.data.ProductRepository
import com.example.shopmanagement.model.Brand
import com.example.shopmanagement.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val productRepository: ProductRepository,
    private val brandRepository: BrandRepository
) : ViewModel() {

    val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())

    val homeScreenUiState = _homeScreenUiState.asStateFlow()


    init {
        viewModelScope.launch {
            _homeScreenUiState.update {
                it.copy(
                    productList = fetchAllProduct(),
                    brandList = fetchAllBrand()
                    )
            }
        }

    }

    private suspend fun fetchAllProduct(): Map<String, Product> {
        return productRepository.fetchAllProducts().stateIn(viewModelScope).value
    }

    private suspend fun fetchAllBrand(): List<Brand> {
        return brandRepository.fetchAllBrand().stateIn(viewModelScope).value
    }


}