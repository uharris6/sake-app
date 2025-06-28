package com.uharris.sakeapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.uharris.sakeapp.R
import com.uharris.sakeapp.ui.theme.SakeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopTopBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = navigationIcon,
        modifier = Modifier.shadow(
            elevation = 4.dp
        )
    )
}

@PreviewLightDark
@Composable
fun ShopTopBarPreview() {
    SakeAppTheme {
        ShopTopBar(stringResource(R.string.app_name))
    }
}