package com.uharris.sakeapp.detail.ui

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.uharris.sakeapp.R
import com.uharris.sakeapp.detail.ShopDetailState
import com.uharris.sakeapp.domain.ShopUi
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

class DetailUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context: Context = getInstrumentation().targetContext
    private val onBack: () -> Unit = mockk(relaxed = true)

    @Test
    fun detailUiTest() {
        composeTestRule.setContent {
            DetailContent(
                shopDetailState = ShopDetailState.Loaded(
                    shop = ShopUi(
                        name = "信州スシサカバ 寿しなの",
                        picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                        rating = 4.5,
                        description = "Sushi bar with a variety of sake options.",
                        address = "〒380-0824 長野県長野市南長野南石堂町1421",
                        website = "",
                        mapLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6"
                    )
                ),
                onBack = onBack,
            )
        }
        composeTestRule.onNodeWithText("信州スシサカバ 寿しなの").assertIsDisplayed()
        composeTestRule.onNodeWithText("Sushi bar with a variety of sake options.")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(context.getString(R.string.shop_website_button_text))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("4.5").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.on_back_button_content_description))
            .assertIsDisplayed()
            .performClick()
        composeTestRule.onNodeWithContentDescription("信州スシサカバ 寿しなの").assertIsDisplayed()
        verify { onBack() }
    }
}