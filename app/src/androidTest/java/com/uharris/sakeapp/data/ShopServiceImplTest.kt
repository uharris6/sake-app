package com.uharris.sakeapp.data

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.uharris.sakeapp.domain.ShopService
import com.uharris.sakeapp.utils.AppResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(
    AndroidJUnit4::class
)
@OptIn(ExperimentalCoroutinesApi::class)
class ShopServiceImplTest {

    private val context: Context = getInstrumentation().targetContext
    private lateinit var shopService: ShopService

    @Before
    fun setUp() {
        shopService = ShopServiceImpl(context, UnconfinedTestDispatcher())
    }

    @Test
    fun shopServiceFetchShopsShouldReturnSuccess() = runTest {
        val result = shopService.fetchShops()
        assert(result is AppResult.Success)
        assert((result as AppResult.Success).data.size == 10)
    }

    @Test
    fun shopServiceGetShopDetailShouldReturnShop() = runTest {
        val result = shopService.fetchShops()
        advanceUntilIdle()
        val shop = shopService.fetchShopDetail(2)
        assert((result as AppResult.Success).data[2] == shop)
    }
}