package com.revolhope.data.common.api

import com.revolhope.data.feature.film.response.FilmsResponse
import com.revolhope.data.feature.search.response.CharacterResponse
import com.revolhope.data.feature.search.response.CharactersResponse
import com.revolhope.data.feature.search.response.PlanetResponse
import com.revolhope.data.feature.search.response.PlanetsResponse
import com.revolhope.data.feature.search.response.SpecieResponse
import com.revolhope.data.feature.search.response.SpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Star wars Api interface
 */
interface StarWarsApi {

    @GET("api/films")
    suspend fun fetchFilms(): FilmsResponse

    @GET("api/people")
    suspend fun fetchCharacters(@Query("page") page: Int): CharactersResponse

    @GET("api/people/{id}")
    suspend fun fetchCharacterById(@Path("id") id: Int): CharacterResponse

    @GET("api/species")
    suspend fun fetchSpecies(@Query("page") page: Int): SpeciesResponse

    @GET("api/species/{id}")
    suspend fun fetchSpecieById(@Path("id") id: Int): SpecieResponse

    @GET("api/planets")
    suspend fun fetchPlanets(@Query("page") page: Int): PlanetsResponse

    @GET("api/planets/{id}")
    suspend fun fetchPlanetById(@Path("id") id: Int): PlanetResponse
}
