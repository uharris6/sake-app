package com.uharris.sakeapp.detail

import app.cash.turbine.test
import com.uharris.sakeapp.domain.ShopService
import com.uharris.sakeapp.domain.ShopUi
import com.uharris.sakeapp.utils.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val shopService: ShopService = mockk(relaxed = true)
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel(shopService)
    }

    @Test
    fun `shopDetailState should return None if shopService return a null shop`() = runTest {
        every { shopService.fetchShopDetail(any()) } returns null

        viewModel.fetchShopDetail(2)

        viewModel.shopDetailState.test {
            assertEquals(ShopDetailState.None, awaitItem())
        }
    }

    @Test
    fun `shopDetailState should return Load if shopService return a shop`() = runTest {
        val shop = ShopUi(
            name = "信州スシサカバ 寿しなの",
            picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
            rating = 4.5,
            description = "Sushi bar with a variety of sake options.",
            address = "〒380-0824 長野県長野市南長野南石堂町1421",
            website = "",
            mapLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6"
        )
        every { shopService.fetchShopDetail(any()) } returns shop

        viewModel.fetchShopDetail(2)

        viewModel.shopDetailState.test {
            assertEquals(
                ShopDetailState.Loaded(
                    shop
                ), awaitItem()
            )
        }
    }
}