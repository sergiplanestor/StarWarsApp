package com.revolhope.data.feature.search.datasource

import com.revolhope.data.common.api.StarWarsApi
import com.revolhope.data.feature.film.response.FilmsResponse
import com.revolhope.data.feature.search.response.CharacterResponse
import com.revolhope.data.feature.search.response.CharactersResponse
import com.revolhope.data.feature.search.response.PlanetResponse
import com.revolhope.data.feature.search.response.PlanetsResponse
import com.revolhope.data.feature.search.response.SpecieResponse
import com.revolhope.data.feature.search.response.SpeciesResponse
import javax.inject.Inject

class SearchNetworkDataSource @Inject constructor(private val restApi: StarWarsApi) {

    suspend fun fetchCharacters(page: Int): CharactersResponse = restApi.fetchCharacters(page)

    suspend fun fetchCharacterById(id: Int): CharacterResponse = restApi.fetchCharacterById(id)

    suspend fun fetchSpecies(page: Int): SpeciesResponse = restApi.fetchSpecies(page)

    suspend fun fetchPlanets(page: Int): PlanetsResponse = restApi.fetchPlanets(page)

    suspend fun fetchPlanetById(id: Int): PlanetResponse = restApi.fetchPlanetById(id)
}
