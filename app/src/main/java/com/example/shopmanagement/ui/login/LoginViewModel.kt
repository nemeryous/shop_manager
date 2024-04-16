package com.example.shopmanagement.ui.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shopmanagement.data.service.AccountService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(private val accountService: AccountService) : ViewModel() {
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

        try {
            accountService.signInWithEmailAndPassword(
                _loginUiState.value.email,
                _loginUiState.value.password
            )
            navigateToHome()
        } catch (e: Exception) {
            Log.d(TAG, "Wrong password or wrong email")
            _loginUiState.value = _loginUiState.value.copy(
                loginError = true
            )
        }
    }

}