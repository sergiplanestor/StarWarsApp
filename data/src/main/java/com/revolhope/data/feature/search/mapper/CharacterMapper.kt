package com.revolhope.data.feature.search.mapper

import com.revolhope.data.common.date.DateMapper
import com.revolhope.data.common.extensions.lastUrlSegmentAsInt
import com.revolhope.data.feature.search.response.CharacterResponse
import com.revolhope.domain.common.extensions.letOr
import com.revolhope.domain.common.model.date.DateModel
import com.revolhope.domain.feature.search.model.CharacterModel

object CharacterMapper {
    fun fromCharacterResponseToModel(response: CharacterResponse): CharacterModel =
        CharacterModel(
            name = response.name.orEmpty(),
            height = response.height.orEmpty(),
            weight = response.weight.orEmpty(),
            hairColor = response.hairColor.orEmpty(),
            skinColor = response.skinColor.orEmpty(),
            eyeColor = response.eyeColor.orEmpty(),
            birthYear = response.birthYear.orEmpty(),
            gender = response.gender.orEmpty(),
            homeWorldId = response.homeWorld.lastUrlSegmentAsInt() ?: Int.MIN_VALUE,
            speciesIds = response.species?.mapNotNull { it.lastUrlSegmentAsInt() } ?: emptyList(),
            vehiclesIds = response.vehicles?.mapNotNull { it.lastUrlSegmentAsInt() } ?: emptyList(),
            starshipsIds = response.starships?.mapNotNull { it.lastUrlSegmentAsInt() }
                ?: emptyList(),
            createdOn = response.createdOn.letOr(
                default = DateModel.empty,
                block = DateMapper::fromISO8610ToModel
            ),
            editedOn = response.editedOn.letOr(
                default = DateModel.empty,
                block = DateMapper::fromISO8610ToModel
            ),
            url = response.url.orEmpty()
        )
}
