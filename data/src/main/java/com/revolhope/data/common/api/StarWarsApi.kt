package com.revolhope.data.common.api

import com.revolhope.data.feature.film.response.FilmsResponse
import retrofit2.http.GET

interface StarWarsApi {

    @GET("api/films")
    suspend fun fetchFilms(): FilmsResponse

}
