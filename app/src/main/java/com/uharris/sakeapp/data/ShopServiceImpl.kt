package com.uharris.sakeapp.data

import android.content.Context
import com.uharris.sakeapp.data.dto.Shop
import com.uharris.sakeapp.domain.ShopService
import com.uharris.sakeapp.domain.ShopUi
import com.uharris.sakeapp.utils.AppResult
import com.uharris.sakeapp.utils.toShopUI
import java.io.IOException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ShopServiceImpl(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher,
) : ShopService {

    private var shopUiList: List<ShopUi>? = null

    override suspend fun fetchShops(): AppResult<List<ShopUi>> =
        withContext(ioDispatcher) {
            try {
                AppResult.Success(
                    convertJsonToShopUi(readJsonFromAssets(context))
                )
            } catch (ioException: IOException) {
                AppResult.Error(ioException)
            }
        }

    override fun fetchShopDetail(index: Int): ShopUi? =
        shopUiList?.get(index)

    private fun readJsonFromAssets(context: Context): String {
        return context.assets.open(SAKE_SHOP_JSON).bufferedReader().use { it.readText() }
    }

    private fun convertJsonToShopUi(json: String): List<ShopUi> =
            Json.decodeFromString<List<Shop>>(json).map { shop ->
                shop.toShopUI()
            }.also {
                shopUiList = it
            }

    companion object {
        private const val SAKE_SHOP_JSON = "SakeShop.json"
    }
}