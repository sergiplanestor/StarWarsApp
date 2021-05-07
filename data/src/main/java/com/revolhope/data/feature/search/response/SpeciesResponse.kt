package com.revolhope.data.feature.search.response

import com.google.gson.annotations.SerializedName

data class SpeciesResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: Int?,
    @SerializedName("previous") val previous: Int?,
    @SerializedName("results") val results: List<SpecieResponse>?
)
