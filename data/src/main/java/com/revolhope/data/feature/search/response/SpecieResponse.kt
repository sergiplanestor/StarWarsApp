package com.revolhope.data.feature.search.response

import com.google.gson.annotations.SerializedName

data class SpecieResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("classification") val classification: String?,
    @SerializedName("designation") val designation: String?,
    @SerializedName("average_height") val heightAverage: String?,
    @SerializedName("average_lifespan") val lifeSpanAverage: String?,
    @SerializedName("skin_colors") val skinColor: String?,
    @SerializedName("hair_colors") val hairColor: String?,
    @SerializedName("eye_colors") val eyeColor: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("homeworld") val homeWorld: String?,
    @SerializedName("people") val characters: List<String>?,
    @SerializedName("films") val films: List<String>?,
    @SerializedName("created") val createdOn: String?,
    @SerializedName("edited") val editedOn: String?,
    @SerializedName("url") val url: String?
)
