package com.revolhope.data.feature.search.datasource

import com.revolhope.data.feature.search.response.CharacterResponse
import com.revolhope.data.feature.search.response.PlanetResponse
import com.revolhope.data.feature.search.response.SpecieResponse

object SearchCacheDataSource {

    // Private attributes
    private val _characters: MutableList<CharacterResponse> = mutableListOf()
    val characters: List<CharacterResponse> get() = _characters

    private val _species: MutableList<SpecieResponse> = mutableListOf()
    val species: List<SpecieResponse> get() = _species

    private val _planets: MutableList<PlanetResponse> = mutableListOf()
    val planets: List<PlanetResponse> get() = _planets

    // Public methods
    fun insertCharacters(items: List<CharacterResponse>) {
        _characters.addAll(items)
    }

    fun insertSpecies(items: List<SpecieResponse>) {
        _species.addAll(items)
    }

    fun insertPlanets(items: List<PlanetResponse>) {
        _planets.addAll(items)
    }

    fun clearCache() {
        _characters.clear()
        _species.clear()
        _planets.clear()
    }
}
