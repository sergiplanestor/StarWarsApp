package com.revolhope.domain.feature.film.repository

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.film.model.FilmModel

interface FilmRepository {

    suspend fun fetchEpisodes(): State<List<FilmModel>>

}
