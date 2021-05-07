package com.revolhope.domain.feature.search.model

import com.revolhope.domain.common.model.date.DateModel

data class PlanetModel(
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWaterPercentage: String,
    val population: String,
    val residentsUrl: List<String>,
    val filmsUrl: List<String>,
    val createdOn: DateModel,
    val editedOn: DateModel,
    val url: String
)
