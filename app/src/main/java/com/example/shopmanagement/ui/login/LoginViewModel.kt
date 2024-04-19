package com.example.shopmanagement.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.Resource
import com.example.shopmanagement.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    val _loginUiState = MutableStateFlow(LoginUiState())

    // Backing property
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    var checkVisible by mutableStateOf(false)
        private set


    fun updateEmail(email: String) {
        _loginUiState.value = _loginUiState.value.copy(
            email = email
        )
    }

    fun updatePassword(password: String) {
        _loginUiState.value = _loginUiState.value.copy(
            password = password
        )
    }

    fun updateCheckVisible() {
        checkVisible = !checkVisible
    }

    fun validateInput(): Boolean {
        return _loginUiState.value.let {
            it.email.isNotBlank() && it.password.isNotBlank()
        }
    }

    suspend fun login(navigateToHome: () -> Unit) {
        viewModelScope.launch {
            authRepository.loginUser(email = _loginUiState.value.email, password = _loginUiState.value.password).collectLatest {
                result ->
                when (result) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        navigateToHome()
                    }

                    is Resource.Error -> {
                        _loginUiState.update { it.copy(loginError = true) }
                    }
                }
            }
        }
    }

}