package com.example.shopmanagement


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.shopmanagement.ui.admin.BrandAddViewModel
import com.example.shopmanagement.ui.admin.CategoryAddViewModel
import com.example.shopmanagement.ui.admin.ProductAddViewModel
import com.example.shopmanagement.ui.home.HomeScreenViewModel
import com.example.shopmanagement.ui.login.LoginViewModel
import com.example.shopmanagement.ui.login.SignUpViewModel
import com.example.shopmanagement.ui.product.ProductDetailScreen
import com.example.shopmanagement.ui.product.ProductDetailsViewModel

object AppViewModelProvider {

    val Factory = viewModelFactory {
        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            SignUpViewModel(
                authRepository = application.container.authRepository
            )
        }

        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            LoginViewModel(
                authRepository = application.container.authRepository
            )
        }

        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            ProductAddViewModel(
                productRepository = application.container.productRepository,
                imageRepository = application.container.imageRepository,
                brandRepository = application.container.brandRepository
            )
        }

        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            BrandAddViewModel(
                brandRepository = application.container.brandRepository
            )
        }

        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            CategoryAddViewModel(
                categoryRepository = application.container.categoryRepository
            )
        }

        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            HomeScreenViewModel(
                productRepository = application.container.productRepository,
                brandRepository = application.container.brandRepository
            )
        }

        initializer {
            val application = (this[APPLICATION_KEY] as ShopManagementApplication)
            ProductDetailsViewModel(
                this.createSavedStateHandle(),
                productRepository = application.container.productRepository,
                imageRepository = application.container.imageRepository
            )
        }


    }
}

fun CreationExtras.shopManagementApplication(): ShopManagementApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ShopManagementApplication)