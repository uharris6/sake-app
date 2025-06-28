package com.uharris.sakeapp.data

import android.content.Context
import com.uharris.sakeapp.domain.ShopService
import com.uharris.sakeapp.utils.AppResult
import io.mockk.mockk
import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ShopServiceImplTest {

    private val context: Context = mockk(relaxed = true)

    private lateinit var shopService: ShopService

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        shopService = ShopServiceImpl(context, UnconfinedTestDispatcher())
    }

    @Test
    @Throws(IOException::class)
    fun shopServiceFetchShopsShouldThrowException() = runTest {
        val result = shopService.fetchShops()
        assert(result is AppResult.Error)
    }



}