package com.example.shopmanagement.ui.admin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shopmanagement.data.BrandRepository
import com.example.shopmanagement.extension.toBrand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BrandAddViewModel(
    private val brandRepository: BrandRepository,
) : ViewModel() {

    val _brandAddUiState = MutableStateFlow(BrandAddUiState())

    val brandAddUiState = _brandAddUiState.asStateFlow()

    var isChooseImage by mutableStateOf(false)

    fun updateBrandName(name: String) {
        _brandAddUiState.update { it.copy(brandName = name) }
    }

    fun updateBrandDescription(description: String) {
        _brandAddUiState.update { it.copy(brandDescription = description) }
    }

    fun updateBrandImageUrl(imageUrl: String) {
        _brandAddUiState.update { it.copy(brandImageUrl = imageUrl) }
    }

    suspend fun addBrand() {
        brandRepository.addBrand(_brandAddUiState.value.toBrand())
    }

}