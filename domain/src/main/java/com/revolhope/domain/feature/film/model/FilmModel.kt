package com.revolhope.domain.feature.film.model

import android.os.Parcelable
import com.revolhope.domain.common.model.date.DateModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmModel(
    val title: String,
    val episodeNum: Int,
    val description: String,
    val director: String,
    val producer: String,
    val releasedOn: DateModel,
    val charactersIds: List<Int>,
    val planetsIds: List<Int>,
    val starshipsIds: List<Int>,
    val vehiclesIds: List<Int>,
    val speciesIds: List<Int>,
    val createdOn: DateModel,
    val editedOn: DateModel,
    val extraInfoUrl: String
): Parcelable
