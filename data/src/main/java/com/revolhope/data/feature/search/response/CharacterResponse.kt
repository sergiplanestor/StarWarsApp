package com.revolhope.data.feature.search.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("mass") val weight: String?,
    @SerializedName("hair_color") val hairColor: String?,
    @SerializedName("skin_color") val skinColor: String?,
    @SerializedName("eye_color") val eyeColor: String?,
    @SerializedName("birth_year") val birthYear: String?,
    @SerializedName("gender") val gender: String?,
    @SerializedName("homeworld") val homeWorld: String?,
    @SerializedName("species") val species: List<String>?,
    @SerializedName("vehicles") val vehicles: List<String>?,
    @SerializedName("starships") val starships: List<String>?,
    @SerializedName("created") val createdOn: String?,
    @SerializedName("edited") val editedOn: String?,
    @SerializedName("url") val url: String?
)
