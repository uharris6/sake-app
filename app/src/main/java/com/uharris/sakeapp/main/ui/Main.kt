package com.uharris.sakeapp.main.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uharris.sakeapp.R
import com.uharris.sakeapp.domain.ShopUi
import com.uharris.sakeapp.main.MainViewModel
import com.uharris.sakeapp.main.ShopState
import com.uharris.sakeapp.ui.components.ShopImage
import com.uharris.sakeapp.ui.components.ShopRating
import com.uharris.sakeapp.ui.components.ShopTopBar
import com.uharris.sakeapp.ui.theme.SakeAppTheme
import com.uharris.sakeapp.utils.ShopTestTags.shopListTestTag
import com.uharris.sakeapp.utils.ShopTestTags.shopListTestTagItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainUi(
    viewModel: MainViewModel = koinViewModel(),
    onShopClick: (Int) -> Unit,
) {
    val shopState by viewModel.shopState.collectAsStateWithLifecycle()

    MainContent(
        shopState = shopState,
        onShopClick = onShopClick
    )
}

@Composable
fun MainContent(
    shopState: ShopState,
    onShopClick: (Int) -> Unit,
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            ShopTopBar(stringResource(R.string.toolbar_main_title))
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (shopState) {
                ShopState.Loading -> CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .semantics {
                            contentDescription = context.getString(R.string.shop_list_loading)
                        }
                )

                is ShopState.LoadShops -> {
                    ShopList(
                        shopList = shopState.shops,
                        onShopClick = onShopClick
                    )
                }

                is ShopState.Error -> {
                    Text(
                        text = stringResource(R.string.shop_list_error),
                        modifier = Modifier
                            .align(alignment = Alignment.Center)
                            .padding(horizontal = 16.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun ShopList(
    shopList: List<ShopUi>,
    onShopClick: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.testTag(shopListTestTag)
    ) {
        itemsIndexed(shopList, key = { _, shop -> shop.hashCode() }) { index, shop ->
            ShopItem(
                shop = shop,
                index = index,
                onShopClick = onShopClick,
                modifier = Modifier.testTag(shopListTestTagItem(index))
            )
        }
    }
}

@Composable
fun ShopItem(
    shop: ShopUi,
    index: Int,
    onShopClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onShopClick(index) },
                indication = ripple(),
            )
            .semantics(mergeDescendants = true, {})
    ) {
        ShopImage(picture = shop.picture, contentDescription = shop.name)
        Text(text = shop.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        ShopRating(rating = shop.rating)
    }
}

@PreviewLightDark
@Composable
private fun MainContentPreview() {
    SakeAppTheme {
        MainContent(
            shopState = ShopState.LoadShops(
                listOf(
                    ShopUi(
                        name = "信州スシサカバ 寿しなの",
                        picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                        rating = 4.5,
                        description = "",
                        address = "",
                        website = "",
                        mapLink = ""
                    ),
                    ShopUi(
                        name = "信州スシサカバ 寿しなのh",
                        picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                        rating = 4.5,
                        description = "",
                        address = "",
                        website = "",
                        mapLink = ""
                    )
                )
            ),
            onShopClick = {}
        )
    }
}