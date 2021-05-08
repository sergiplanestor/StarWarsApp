package com.revolhope.data.feature.search.response

import com.google.gson.annotations.SerializedName

data class PlanetResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("rotation_period") val rotationPeriod: String?,
    @SerializedName("orbital_period") val orbitalPeriod: String?,
    @SerializedName("diameter") val diameter: String?,
    @SerializedName("climate") val climate: String?,
    @SerializedName("gravity") val gravity: String?,
    @SerializedName("terrain") val terrain: String?,
    @SerializedName("surface_water") val surfaceWaterPercentage: String?,
    @SerializedName("population") val population: String?,
    @SerializedName("residents") val residents: List<String>?,
    @SerializedName("films") val films: List<String>?,
    @SerializedName("created") val createdOn: String?,
    @SerializedName("edited") val editedOn: String?,
    @SerializedName("url") val url: String?
)
