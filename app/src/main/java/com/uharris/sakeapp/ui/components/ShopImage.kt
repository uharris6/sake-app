package com.uharris.sakeapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.uharris.sakeapp.R

@Composable
fun ShopImage(picture: String?, contentDescription: String) {
    AsyncImage(
        model = picture,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.image_placeholder),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}