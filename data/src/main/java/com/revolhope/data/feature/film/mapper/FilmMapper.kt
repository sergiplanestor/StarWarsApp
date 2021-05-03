package com.revolhope.data.feature.film.mapper

import com.revolhope.data.common.date.DateMapper
import com.revolhope.data.feature.film.response.FilmResponse
import com.revolhope.domain.common.extensions.SLASH
import com.revolhope.domain.common.extensions.letOr
import com.revolhope.domain.common.extensions.remove
import com.revolhope.domain.common.model.date.DateModel
import com.revolhope.domain.feature.film.model.FilmModel

object FilmMapper {

    fun fromFilmResponseToModel(response: FilmResponse): FilmModel =
        FilmModel(
            title = response.title.orEmpty(),
            episodeNum = response.episodeId ?: Int.MIN_VALUE,
            description = response.crawl.orEmpty(),
            director = response.director.orEmpty(),
            producer = response.producer.orEmpty(),
            charactersIds = response.characters?.mapNotNull(::lastUrlSegmentAsInt) ?: emptyList(),
            planetsIds = response.planets?.mapNotNull(::lastUrlSegmentAsInt) ?: emptyList(),
            speciesIds = response.species?.mapNotNull(::lastUrlSegmentAsInt) ?: emptyList(),
            starshipsIds = response.starships?.mapNotNull(::lastUrlSegmentAsInt) ?: emptyList(),
            vehiclesIds = response.vehicles?.mapNotNull(::lastUrlSegmentAsInt) ?: emptyList(),
            createdOn = response.createdOn.letOr(
                default = DateModel.empty,
                block = DateMapper::fromISO8610ToModel
            ),
            releasedOn = response.releasedOn.letOr(
                default = DateModel.empty,
                block = DateMapper::fromSimpleFormatToModel
            ),
            editedOn = response.editedOn.letOr(
                default = DateModel.empty,
                block = DateMapper::fromISO8610ToModel
            ),
            extraInfoUrl = response.url.orEmpty()
        )

    private fun lastUrlSegmentAsInt(url: String?): Int? =
        url?.split(SLASH).run { this?.get(lastIndex)?.remove(SLASH)?.toIntOrNull() }
}
