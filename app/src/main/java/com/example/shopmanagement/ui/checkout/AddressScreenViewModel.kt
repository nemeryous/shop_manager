package com.example.shopmanagement.ui.checkout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopmanagement.data.ShippingAddressRepository
import com.example.shopmanagement.model.ShippingAddress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddressScreenViewModel(
    val shippingAddressRepository: ShippingAddressRepository
) : ViewModel() {


    val _shippingAddressList = MutableStateFlow<List<ShippingAddress>>(emptyList())

    val shippingAddressList = _shippingAddressList.asStateFlow()

    init {
        getAddressList()
    }
    fun getAddressList() {
        viewModelScope.launch {
            _shippingAddressList.value = shippingAddressRepository.getUserShippingAddress()
        }

    }


}

class SharedViewModel : ViewModel() {
    val selectedAddress = MutableLiveData<ShippingAddress>(null)
}