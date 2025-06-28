package com.uharris.sakeapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uharris.sakeapp.domain.ShopService
import com.uharris.sakeapp.utils.AppResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val shopService: ShopService
): ViewModel() {

    private val _shopState: MutableStateFlow<ShopState> = MutableStateFlow(ShopState.Loading)
    val shopState: StateFlow<ShopState> get() = _shopState

    init {
        viewModelScope.launch {
            _shopState.value = when(val result = shopService.fetchShops()) {
                is AppResult.Success -> ShopState.LoadShops(result.data)
                is AppResult.Error -> ShopState.Error
            }
        }
    }
}