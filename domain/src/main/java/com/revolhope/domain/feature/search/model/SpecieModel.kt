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
    val homeWorldId: Int,
    val charactersIds: List<Int>,
    val filmsIds: List<Int>,
    val createdOn: DateModel,
    val editedOn: DateModel,
    val url: String
)
