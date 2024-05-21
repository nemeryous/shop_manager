package com.example.shopmanagement.ui.admin

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.BrandRepository
import com.example.shopmanagement.data.ImageRepository
import com.example.shopmanagement.extension.toBrand
import com.example.shopmanagement.model.Brand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BrandAddViewModel(
    private val brandRepository: BrandRepository,
    private val imageRepository: ImageRepository
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

    suspend fun addBrand(bitmap: Bitmap) {
        var imgUrl = ""
        viewModelScope.launch {
            imgUrl = imageRepository.addImage(bitmap)
        }.join()

        _brandAddUiState.update { it.copy(brandImageUrl = imgUrl) }

        brandRepository.addBrand(_brandAddUiState.value.toBrand())

    }



}