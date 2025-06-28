package com.uharris.sakeapp.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Shop (
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val coordinates: List<Double>,
    val website: String,
    @SerialName("google_maps_link")
    val googleMapsLink: String
)