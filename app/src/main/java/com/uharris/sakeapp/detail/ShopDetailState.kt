package com.uharris.sakeapp.detail

import com.uharris.sakeapp.domain.ShopUi

sealed class ShopDetailState {
    data object None : ShopDetailState()
    data class Loaded(val shop: ShopUi) : ShopDetailState()
}