package com.example.shopmanagement.ui.admin.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserScreenViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserScreenUiState())
    val uiState = _uiState.asStateFlow()
    init {
        viewModelScope.launch {
            _uiState.update { it.copy(userList = getListUser()) }
        }
    }

    private suspend fun getListUser(): List<User> {
        return authRepository.fetchAllUser().stateIn(viewModelScope).value
    }

}

data class UserScreenUiState(
    val userList: List<User> = emptyList()
)