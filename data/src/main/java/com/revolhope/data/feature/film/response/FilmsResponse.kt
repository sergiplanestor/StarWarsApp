package com.revolhope.data.feature.film.response

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: Int?,
    @SerializedName("previous") val previous: Int?,
    @SerializedName("results") val results: List<FilmResponse>?
)
