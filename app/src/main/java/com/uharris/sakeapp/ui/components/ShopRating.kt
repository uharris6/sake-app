package com.uharris.sakeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uharris.sakeapp.utils.ShopTestTags

@Composable
fun ShopRating(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .semantics(mergeDescendants = true, {})
            .testTag(ShopTestTags.shopRatingTestTag)
    ) {
        Text(rating.toString(), fontSize = 18.sp)
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = Icons.Filled.Star,
            contentDescription = null
        )
    }
}