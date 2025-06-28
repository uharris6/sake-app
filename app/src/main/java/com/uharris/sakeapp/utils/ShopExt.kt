package com.uharris.sakeapp.utils

import com.uharris.sakeapp.data.dto.Shop
import com.uharris.sakeapp.domain.ShopUi

fun Shop.toShopUI() = ShopUi(
    name = name,
    description = description,
    picture = picture,
    rating = rating,
    address = address,
    website = website,
    mapLink = googleMapsLink
)