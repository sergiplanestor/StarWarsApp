package com.revolhope.domain.feature.search.model

import com.revolhope.domain.common.model.date.DateModel

data class SpecieModel(
    val name: String,
    val classification: String,
    val designation: String,
    val heightAverage: String,
    val lifeSpanAverage: String,
    val skinColor: String,
    val hairColor: String,
    val eyeColor: String,
    val language: String,
    val homeWorld: String,
    val charactersUrl: List<String>,
    val filmsUrl: List<String>,
    val createdOn: DateModel,
    val editedOn: DateModel,
    val url: String
)
