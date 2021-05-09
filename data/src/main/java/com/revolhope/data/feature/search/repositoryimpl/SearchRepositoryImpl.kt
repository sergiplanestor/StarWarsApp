package com.revolhope.data.feature.search.repositoryimpl

import com.revolhope.data.common.base.BaseRepositoryImpl
import com.revolhope.data.feature.search.datasource.SearchCacheDataSource
import com.revolhope.data.feature.search.datasource.SearchNetworkDataSource
import com.revolhope.data.feature.search.datasource.SearchTypeDataSource
import com.revolhope.data.feature.search.mapper.CharacterMapper
import com.revolhope.data.feature.search.mapper.PlanetMapper
import com.revolhope.data.feature.search.mapper.SearchTypeMapper
import com.revolhope.data.feature.search.mapper.SpecieMapper
import com.revolhope.data.feature.search.response.CharacterResponse
import com.revolhope.data.feature.search.response.PlanetResponse
import com.revolhope.data.feature.search.response.SpecieResponse
import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.domain.feature.search.model.SearchTypeModel
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.domain.feature.search.repository.SearchRepository
import javax.inject.Inject

/**
 * Film repository implementation. It extends from [BaseRepositoryImpl] and implements [SearchRepository]
 * interface. Its purpose is to have the logic to decide where the different searched data needs to come from.
 */
class SearchRepositoryImpl @Inject constructor(
    private val typeDataSource: SearchTypeDataSource,
    private val searchNetworkDataSource: SearchNetworkDataSource
) : BaseRepositoryImpl(), SearchRepository {

    override suspend fun fetchSearchType(): State<SearchTypeModel?> =
        statefulBlock {
            typeDataSource.fetchSearchType()?.let(SearchTypeMapper::fromSearchTypeResponseToModel)
        }

    override suspend fun updateSearchType(searchType: SearchTypeModel): State<Boolean> =
        statefulBlock {
            typeDataSource.updateSearchType(searchType.let(SearchTypeMapper::fromSearchTypeModelToResponse))
        }

    override suspend fun fetchCharactersByName(name: String): State<List<CharacterModel>> =
        statefulBlock {
            fetchCharacters().filter {
                it.name?.contains(name, ignoreCase = true) == true
            }.map(CharacterMapper::fromCharacterResponseToModel)
        }

    override suspend fun fetchCharacterByIds(ids: List<Int>): State<List<CharacterModel>> =
        statefulBlock {
            val result = mutableListOf<CharacterModel>()
            ids.forEach {
                result.add(searchNetworkDataSource.fetchCharacterById(it)
                    .let(CharacterMapper::fromCharacterResponseToModel)
                )
            }
            result
        }

    override suspend fun fetchPlanetsByName(name: String): State<List<PlanetModel>> =
        statefulBlock {
            fetchPlanets().filter {
                it.name?.contains(name, ignoreCase = true) == true
            }.map(PlanetMapper::fromPlanetResponseToModel)
        }

    override suspend fun fetchPlanetsByIds(ids: List<Int>): State<List<PlanetModel>> =
        statefulBlock {
            val result = mutableListOf<PlanetModel>()
            ids.forEach {
                result.add(searchNetworkDataSource.fetchPlanetById(it)
                    .let(PlanetMapper::fromPlanetResponseToModel)
                )
            }
            result
        }

    override suspend fun fetchSpeciesByName(name: String): State<List<SpecieModel>> =
        statefulBlock {
            fetchSpecies().filter {
                it.name?.contains(name, ignoreCase = true) == true
            }.map(SpecieMapper::fromSpecieResponseToModel)
        }

    private suspend fun fetchCharacters(): List<CharacterResponse> {
        if (SearchCacheDataSource.characters.isEmpty()) {
            var page = 0
            var isNext: Boolean
            do {
                page++
                searchNetworkDataSource.fetchCharacters(page).run {
                    isNext = next.isNullOrBlank().not()
                    SearchCacheDataSource.insertCharacters(items = results ?: emptyList())
                }
            } while (isNext)

        }
        return SearchCacheDataSource.characters
    }

    private suspend fun fetchPlanets(): List<PlanetResponse> {
        if (SearchCacheDataSource.planets.isEmpty()) {
            var page = 0
            var isNext: Boolean
            do {
                page++
                searchNetworkDataSource.fetchPlanets(page).run {
                    isNext = next.isNullOrBlank().not()
                    SearchCacheDataSource.insertPlanets(items = results ?: emptyList())
                }
            } while (isNext)

        }
        return SearchCacheDataSource.planets
    }

    private suspend fun fetchSpecies(): List<SpecieResponse> {
        if (SearchCacheDataSource.species.isEmpty()) {
            var page = 0
            var isNext: Boolean
            do {
                page++
                searchNetworkDataSource.fetchSpecies(page).run {
                    isNext = next.isNullOrBlank().not()
                    SearchCacheDataSource.insertSpecies(items = results ?: emptyList())
                }
            } while (isNext)

        }
        return SearchCacheDataSource.species
    }
}
