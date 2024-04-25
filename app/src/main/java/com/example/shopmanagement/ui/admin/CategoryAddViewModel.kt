package com.example.shopmanagement.ui.admin

import androidx.lifecycle.ViewModel
import com.example.shopmanagement.data.CategoryRepository
import com.example.shopmanagement.extension.toCategory
import com.example.shopmanagement.ui.CategoryAddUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CategoryAddViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    val _categoryAddUiState = MutableStateFlow(CategoryAddUiState())

    val categoryAddUiState = _categoryAddUiState.asStateFlow()

    fun updateCategoryName(name: String) {
        _categoryAddUiState.update { it.copy(categoryName = name) }
    }

    fun updateCategoryDesc(desc: String) {
        _categoryAddUiState.update { it.copy(categoryDesc = desc) }
    }

    suspend fun addCategory() {
        categoryRepository.addCategory(_categoryAddUiState.value.toCategory())
    }

}