package com.revolhope.data.feature.search.mapper

import com.revolhope.data.common.date.DateMapper
import com.revolhope.data.common.extensions.lastUrlSegmentAsInt
import com.revolhope.data.feature.search.response.PlanetResponse
import com.revolhope.data.feature.search.response.PlanetsResponse
import com.revolhope.domain.common.extensions.letOr
import com.revolhope.domain.common.model.date.DateModel
import com.revolhope.domain.feature.search.model.PlanetModel

object PlanetMapper {

    fun fromPlanetsResponseToModel(response: PlanetsResponse): List<PlanetModel> =
        response.results?.map(::fromPlanetResponseToModel) ?: emptyList()

    private fun fromPlanetResponseToModel(response: PlanetResponse): PlanetModel =
        PlanetModel(
            name = response.name.orEmpty(),
            rotationPeriod = response.rotationPeriod.orEmpty(),
            orbitalPeriod = response.orbitalPeriod.orEmpty(),
            diameter = response.diameter.orEmpty(),
            climate = response.climate.orEmpty(),
            gravity = response.gravity.orEmpty(),
            terrain = response.terrain.orEmpty(),
            surfaceWaterPercentage = response.surfaceWaterPercentage.orEmpty(),
            population = response.population.orEmpty(),
            residentsIds = response.residents?.mapNotNull { it.lastUrlSegmentAsInt() } ?: emptyList(),
            filmsIds = response.films?.mapNotNull { it.lastUrlSegmentAsInt() } ?: emptyList(),
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
