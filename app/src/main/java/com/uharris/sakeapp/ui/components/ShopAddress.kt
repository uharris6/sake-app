package com.uharris.sakeapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import com.uharris.sakeapp.R

@Composable
fun ShopAddress(address: String, mapLink: String) {
    Text(
        buildAnnotatedString {
            append(stringResource(R.string.shop_address_label))
            withLink(
                LinkAnnotation.Url(
                    mapLink,
                    TextLinkStyles(style = SpanStyle(color = Color.Blue))
                )
            ) {
                append(address)
            }
        }
    )
}