package com.example.shopmanagement



import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.shopmanagement.ui.login.LoginViewModel
import com.example.shopmanagement.ui.login.SignUpViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            SignUpViewModel(
               accountService = application.container.accountService
            )
        }

        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            LoginViewModel(
                accountService = application.container.accountService
            )
        }
    }
}

fun CreationExtras.shopManagementApplication(): ShopManagementApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShopManagementApplication)