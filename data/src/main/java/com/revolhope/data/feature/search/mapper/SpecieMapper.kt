package com.revolhope.data.feature.search.mapper

import com.revolhope.data.common.date.DateMapper
import com.revolhope.data.common.extensions.lastUrlSegmentAsInt
import com.revolhope.data.feature.search.response.SpecieResponse
import com.revolhope.data.feature.search.response.SpeciesResponse
import com.revolhope.domain.common.extensions.letOr
import com.revolhope.domain.common.model.date.DateModel
import com.revolhope.domain.feature.search.model.SpecieModel

object SpecieMapper {

    fun fromSpeciesResponseToModel(response: SpeciesResponse): List<SpecieModel> =
        response.results?.map(::fromSpecieResponseToModel) ?: emptyList()

    private fun fromSpecieResponseToModel(response: SpecieResponse): SpecieModel =
        SpecieModel(
            name = response.name.orEmpty(),
            classification = response.classification.orEmpty(),
            designation = response.designation.orEmpty(),
            heightAverage = response.heightAverage.orEmpty(),
            lifeSpanAverage = response.lifeSpanAverage.orEmpty(),
            language = response.language.orEmpty(),
            homeWorldId = response.homeWorld.lastUrlSegmentAsInt() ?: Int.MIN_VALUE,
            charactersIds = response.characters?.mapNotNull { it.lastUrlSegmentAsInt() } ?: emptyList(),
            filmsIds = response.films?.mapNotNull { it.lastUrlSegmentAsInt() } ?: emptyList(),
            hairColor = response.hairColor.orEmpty(),
            skinColor = response.skinColor.orEmpty(),
            eyeColor = response.eyeColor.orEmpty(),
            createdOn = response.createdOn.letOr(
                default = DateModel.empty,
                block = DateMapper::fromISO8610ToModel
            ) ,
            editedOn = response.editedOn.letOr(
                default = DateModel.empty,
                block = DateMapper::fromISO8610ToModel
            ),
            url = response.url.orEmpty()
        )

}
