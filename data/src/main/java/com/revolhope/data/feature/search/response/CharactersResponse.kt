package com.revolhope.data.feature.search.response

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<CharacterResponse>?
)
