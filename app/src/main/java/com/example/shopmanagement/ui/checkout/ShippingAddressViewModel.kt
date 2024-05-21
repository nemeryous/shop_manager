package com.example.shopmanagement.ui.checkout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ShippingAddressRepository
import com.example.shopmanagement.extension.toShippingAddress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShippingAddressViewModel(
    val shippingAddressRepository: ShippingAddressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShippingAddressUiState())
    val uiState = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { it.copy(shippingName = name) }
    }

    fun updatePhone(phone: String) {
        _uiState.update { it.copy(shippingPhone = phone) }
    }

    fun updateStreet(street: String) {
        _uiState.update { it.copy(shippingStreet = street) }
    }

    fun updateCommune(commune: String) {
        _uiState.update { it.copy(shippingCommune = commune) }
    }

    fun updateDistrict(district: String) {
        _uiState.update { it.copy(shippingDistrict = district) }
    }

    fun updateCity(city: String) {
        _uiState.update { it.copy(shippingCity = city) }
    }

    fun savedAddress() {


        val newShippingAddress = uiState.value.toShippingAddress()
        Log.d("ShippingAddressViewModel", newShippingAddress.toString())
        viewModelScope.launch {
            shippingAddressRepository.saveShippingAddress(newShippingAddress)
        }

    }


}

data class ShippingAddressUiState(
    val shippingName: String = "",
    val shippingPhone: String = "",
    val shippingStreet: String = "",
    val shippingCommune: String = "",
    val shippingDistrict: String = "",
    val shippingCity: String = "",
    val shippingAddress: String = "",
)