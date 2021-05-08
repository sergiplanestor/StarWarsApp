package com.revolhope.data.feature.film.datasource

import com.revolhope.data.common.api.StarWarsApi
import com.revolhope.data.feature.film.response.FilmsResponse
import javax.inject.Inject

/**
 * Network data source to fetch films.
 */
class FilmNetworkDataSource @Inject constructor(private val restApi: StarWarsApi) {

    suspend fun fetchFilms(): FilmsResponse = restApi.fetchFilms()
}
