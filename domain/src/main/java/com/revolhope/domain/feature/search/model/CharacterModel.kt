package com.revolhope.domain.feature.search.model

import com.revolhope.domain.common.model.date.DateModel

data class CharacterModel(
    val name: String,
    val height: String,
    val weight: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val homeWorldId: Int,
    val speciesIds: List<Int>,
    val vehiclesIds: List<Int>,
    val starshipsIds: List<Int>,
    val createdOn: DateModel,
    val editedOn: DateModel,
    val url: String
)
