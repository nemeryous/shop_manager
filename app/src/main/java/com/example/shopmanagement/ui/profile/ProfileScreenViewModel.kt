package com.example.shopmanagement.ui.profile

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.AuthRepository
import com.example.shopmanagement.model.User
import com.google.firebase.auth.oAuthCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileScreenViewModel(val authRepository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenUiState())
    val uiState = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            _uiState.update { it.copy(currentUser = getCurrentUser()) }
        }
    }
    private suspend fun getCurrentUser(): User {
        return authRepository.getCurrentUser().stateIn(viewModelScope).value
    }


}

data class ProfileScreenUiState(
    val currentUser: User = User()
)