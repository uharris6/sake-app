package com.uharris.sakeapp.main

import com.uharris.sakeapp.domain.ShopUi

sealed class ShopState {
    data object Loading: ShopState()
    data object Error: ShopState()
    data class LoadShops(val shops: List<ShopUi>): ShopState()
}