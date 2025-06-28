package com.uharris.sakeapp.domain

import com.uharris.sakeapp.utils.AppResult

interface ShopService {
    suspend fun fetchShops(): AppResult<List<ShopUi>>
    fun fetchShopDetail(index: Int): ShopUi?
}