package com.uharris.sakeapp.domain

data class ShopUi(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val website: String,
    val mapLink: String,
)
