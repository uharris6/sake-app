package com.uharris.sakeapp.main

import app.cash.turbine.test
import com.uharris.sakeapp.domain.ShopService
import com.uharris.sakeapp.utils.AppResult
import com.uharris.sakeapp.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {
    @get:Rule
    val mainDispatcherRule =  MainDispatcherRule()

    private val shopService: ShopService = mockk(relaxed = true)

    @Test
    fun `shopState should return Error in the initialization`() = runTest {
        coEvery { shopService.fetchShops() } returns  AppResult.Error(IOException())

        val viewModel = MainViewModel(shopService)

        viewModel.shopState.test {
            assertEquals(ShopState.Error, awaitItem())
        }
    }

    @Test
    fun `shopState should return Success in the initialization`() = runTest {
        coEvery { shopService.fetchShops() } returns  AppResult.Success(listOf())

        val viewModel = MainViewModel(shopService)

        viewModel.shopState.test {
            assertEquals(ShopState.LoadShops(listOf()), awaitItem())
        }
    }
}