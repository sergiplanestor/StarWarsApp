package com.revolhope.domain.feature.search.repository

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.domain.feature.search.model.SearchTypeModel
import com.revolhope.domain.feature.search.model.SpecieModel

interface SearchRepository {

    suspend fun fetchSearchType(): State<SearchTypeModel?>

    suspend fun updateSearchType(searchType: SearchTypeModel): State<Boolean>

    suspend fun fetchCharactersByName(name: String): State<List<CharacterModel>>

    suspend fun fetchCharacterByIds(ids: List<Int>): State<List<CharacterModel>>

    suspend fun fetchPlanetsByName(name: String): State<List<PlanetModel>>

    suspend fun fetchPlanetById(id: Int): State<PlanetModel>

    suspend fun fetchSpeciesByName(name: String): State<List<SpecieModel>>

    suspend fun fetchSpecieById(id: Int): State<SpecieModel>

}
