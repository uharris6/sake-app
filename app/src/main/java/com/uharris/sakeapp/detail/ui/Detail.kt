package com.uharris.sakeapp.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uharris.sakeapp.R
import com.uharris.sakeapp.detail.DetailViewModel
import com.uharris.sakeapp.detail.ShopDetailState
import com.uharris.sakeapp.domain.ShopUi
import com.uharris.sakeapp.ui.components.ShopAddress
import com.uharris.sakeapp.ui.components.ShopImage
import com.uharris.sakeapp.ui.components.ShopRating
import com.uharris.sakeapp.ui.components.ShopTopBar
import com.uharris.sakeapp.ui.theme.SakeAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailUi(
    viewModel: DetailViewModel = koinViewModel(),
    index: Int,
    onBack: () -> Unit,
) {
    val shopDetailState by viewModel.shopDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchShopDetail(shopIndex = index)
    }

    DetailContent(shopDetailState = shopDetailState, onBack = onBack)
}

@Composable
fun DetailContent(shopDetailState: ShopDetailState, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            val title = (shopDetailState as? ShopDetailState.Loaded)?.shop?.name.orEmpty()
            ShopTopBar(
                title = title,
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.on_back_button_content_description)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (shopDetailState) {
                is ShopDetailState.None -> Unit
                is ShopDetailState.Loaded -> {
                    DetailInfo(shop = shopDetailState.shop)
                }
            }
        }
    }
}

@Composable
fun DetailInfo(shop: ShopUi) {
    val uriHandler = LocalUriHandler.current
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        ShopImage(picture = shop.picture, contentDescription = shop.name)
        ShopRating(rating = shop.rating)
        Text(text = shop.description)
        ShopAddress(address = shop.address, mapLink = shop.mapLink)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                uriHandler.openUri(
                    uri = shop.website,
                )
            }
        ) {
            Text(text = stringResource(R.string.shop_website_button_text))
        }
    }
}

@PreviewLightDark
@Composable
fun DetailContentPreview() {
    SakeAppTheme {
        DetailContent(
            shopDetailState = ShopDetailState.Loaded(
                ShopUi(
                    name = "信州スシサカバ 寿しなの",
                    picture = "http://ts1.mm.bing.net/th?id=OIP.GURnZicaENMLYBMZN9k1LwHaFS&pid=15.1",
                    rating = 4.5,
                    description = "Sushi bar with a variety of sake options.",
                    address = "〒380-0824 長野県長野市南長野南石堂町1421",
                    website = "",
                    mapLink = "https://maps.app.goo.gl/4fYMDSfNd6ocsDwt6"
                )
            ),
            onBack = {}
        )
    }
}