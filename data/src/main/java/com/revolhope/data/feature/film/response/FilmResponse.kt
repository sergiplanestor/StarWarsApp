package com.revolhope.data.feature.film.response

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("episode_id") val episodeId: Int?,
    @SerializedName("opening_crawl") val crawl: String?,
    @SerializedName("director") val director: String?,
    @SerializedName("producer") val producer: String?,
    @SerializedName("release_date") val releasedOn: String?,
    @SerializedName("characters") val characters: List<String>?,
    @SerializedName("planets") val planets: List<String>?,
    @SerializedName("starships") val starships: List<String>?,
    @SerializedName("vehicles") val vehicles: List<String>?,
    @SerializedName("species") val species: List<String>?,
    @SerializedName("created") val createdOn: String?,
    @SerializedName("edited") val editedOn: String?,
    @SerializedName("url") val url: String?
)
