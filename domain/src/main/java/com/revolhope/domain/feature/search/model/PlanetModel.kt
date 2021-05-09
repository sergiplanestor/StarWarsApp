package com.revolhope.domain.feature.search.model

import android.os.Parcelable
import com.revolhope.domain.common.model.date.DateModel
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    val residentsIds: List<Int>,
    val filmsIds: List<Int>,
    val createdOn: DateModel,
    val editedOn: DateModel,
    val url: String
): Parcelable
