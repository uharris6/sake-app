package com.uharris.sakeapp.detail

import androidx.lifecycle.ViewModel
import com.uharris.sakeapp.domain.ShopService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(
    private val shopService: ShopService
) : ViewModel() {

    private val _shopDetailState: MutableStateFlow<ShopDetailState> =
        MutableStateFlow(ShopDetailState.None)
    val shopDetailState: StateFlow<ShopDetailState> get() = _shopDetailState

    fun fetchShopDetail(shopIndex: Int) {
        _shopDetailState.value = shopService.fetchShopDetail(shopIndex)?.let {
            ShopDetailState.Loaded(it)
        } ?: ShopDetailState.None
    }
}