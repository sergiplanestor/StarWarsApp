package com.revolhope.data.feature.film.response

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<FilmResponse>?
)
