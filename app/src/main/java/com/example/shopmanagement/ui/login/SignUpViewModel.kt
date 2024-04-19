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
import kotlinx.coroutines.launch


class SignUpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val TAG = SignUpViewModel::class.simpleName
    private val _registrationUiState = MutableStateFlow(RegistrationUiState())

    // Backing property
    val registrationUiState: StateFlow<RegistrationUiState> = _registrationUiState.asStateFlow()

    var checkedPrivacy by mutableStateOf(false)
        private set

    var passwordVisible by mutableStateOf(false)
        private set


    fun updateFirstName(firstName: String) {
        _registrationUiState.value = _registrationUiState.value.copy(
            firstName = firstName
        )
    }

    fun updateLastName(lastName: String) {
        _registrationUiState.value = _registrationUiState.value.copy(
            lastName = lastName
        )
    }

    fun updateEmail(email: String) {
        _registrationUiState.value = _registrationUiState.value.copy(
            email = email
        )
    }

    fun updatePassword(password: String) {
        _registrationUiState.value = _registrationUiState.value.copy(
            password = password
        )
    }

    fun updatePrivacy(checked: Boolean) {
        checkedPrivacy = checked
    }

    fun updatePasswordVisible() {
        passwordVisible = !passwordVisible
    }

    fun validateInput(): Boolean {
        return _registrationUiState.value.let { it ->
            it.firstName.isNotBlank() && it.lastName.isNotBlank() && it.email.isNotBlank() && it.password.isNotBlank()
        }
    }

    suspend fun signUp(navigateToLogin: () -> Unit) {
        viewModelScope.launch {
            authRepository.registerUser(email = _registrationUiState.value.email, password = _registrationUiState.value.password).collectLatest {
                result ->
                when(result) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        navigateToLogin()
                    }

                    is Resource.Error -> {

                    }
                }
            }
        }

    }
}

