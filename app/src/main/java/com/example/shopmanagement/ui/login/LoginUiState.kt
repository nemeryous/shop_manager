package com.example.shopmanagement.ui.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",

    val loginError: Boolean = false
)