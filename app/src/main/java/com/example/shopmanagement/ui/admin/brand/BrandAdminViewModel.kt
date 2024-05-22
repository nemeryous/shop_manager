package com.example.shopmanagement.ui.admin.brand

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.BrandRepository
import com.example.shopmanagement.model.Brand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BrandAdminViewModel(
    val brandRepository: BrandRepository
) : ViewModel() {

    private val _brandList = MutableStateFlow(BrandAdminUiState())
    val brandList = _brandList.asStateFlow()

    init {
        viewModelScope.launch {
            _brandList.update { it.copy(brandList = fetchAllBrands()) }
        }
    }
    private suspend fun fetchAllBrands(): List<Brand> {
        return brandRepository.fetchAllBrand().stateIn(viewModelScope).value
    }

}

data class BrandAdminUiState(
    val brandList: List<Brand> = emptyList()
)