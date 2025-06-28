package com.uharris.sakeapp.main.ui

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.uharris.sakeapp.R
import com.uharris.sakeapp.domain.ShopUi
import com.uharris.sakeapp.main.ShopState
import com.uharris.sakeapp.utils.ShopTestTags
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class MainUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = getInstrumentation().targetContext
    private val onShopClick: (Int) -> Unit = mockk(relaxed = true)

    @Test
    fun mainUiTestError() {
        composeTestRule.setContent {
            MainContent(
                shopState = ShopState.Error,
                onShopClick = onShopClick
            )
        }

        composeTestRule.onNodeWithText(context.getString(R.string.toolbar_main_title))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.shop_list_error))
            .assertIsDisplayed()
    }

    @Test
    fun mainUiTestLoading() {
        composeTestRule.setContent {
            MainContent(
                shopState = ShopState.Loading,
                onShopClick = onShopClick
            )
        }

        composeTestRule.onNodeWithText(context.getString(R.string.toolbar_main_title))
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.shop_list_loading))
            .assertIsDisplayed()
    }

    @Test
    fun mainUiTestLoadShops() {
        composeTestRule.setContent {
            MainContent(
                shopState = ShopState.LoadShops(
                    listOf(
                        ShopUi(
                            name = "信州スシサカバ 寿しなの",
                            description = "Sushi bar with a variety of sake options.",
                            picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                            rating = 4.0,
                            address = "〒380-0824 長野県長野市南長野南石堂町1421",
                            mapLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6",
                            website = "https://www.sushinano.com/"
                        ),
                        ShopUi(
                            name = "長野県酒類販売(株)",
                            description = "Wholesale sake distributor.",
                            picture = "https://www.nagano-sake.com/common/images/front/drinks-xxl@2x.jpg",
                            rating = 4.2,
                            address = "〒380-0835 長野県長野市新田町1464",
                            mapLink = "https://maps.app.goo.gl/wRD6LRQc7Ct9QXMG8",
                            website = "http://www.nagano-sake.com/"
                        )
                    )
                ),
                onShopClick = onShopClick
            )
        }

        composeTestRule.onNodeWithText(context.getString(R.string.toolbar_main_title))
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(ShopTestTags.shopListTestTag)
            .assertIsDisplayed()
        val firstItem = composeTestRule.onNodeWithTag(ShopTestTags.shopListTestTagItem(0), useUnmergedTree = true)
            .performScrollTo()
            .onChildren()
        firstItem
            .filterToOne(hasText("信州スシサカバ 寿しなの"))
            .assertTextEquals("信州スシサカバ 寿しなの")
            .assertIsDisplayed()
        firstItem
            .filterToOne(hasTestTag(ShopTestTags.shopRatingTestTag))
            .onChildren()
            .filterToOne(hasText("4.0"))
            .assertTextEquals("4.0")
            .assertIsDisplayed()

        val secondItem = composeTestRule.onNodeWithTag(ShopTestTags.shopListTestTagItem(1), useUnmergedTree = true)
            .performScrollTo()
            .onChildren()
        secondItem
            .filterToOne(hasText("長野県酒類販売(株)"))
            .assertTextEquals("長野県酒類販売(株)")
            .assertIsDisplayed()
        secondItem
            .filterToOne(hasTestTag(ShopTestTags.shopRatingTestTag))
            .onChildren()
            .filterToOne(hasText("4.2"))
            .assertTextEquals("4.2")
            .assertIsDisplayed()
    }
}