package com.revolhope.data.feature.film.repositoryimpl

import com.revolhope.data.common.base.BaseRepositoryImpl
import com.revolhope.data.feature.film.datasource.FilmCacheDataSource
import com.revolhope.data.feature.film.datasource.FilmNetworkDataSource
import com.revolhope.data.feature.film.mapper.FilmMapper
import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.domain.feature.film.repository.FilmRepository
import javax.inject.Inject

/**
 * Film repository implementation. It extends from [BaseRepositoryImpl] and implements [FilmRepository]
 * interface. Its purpose is to have the logic to decide where the film data needs to come from.
 */
class FilmRepositoryImpl @Inject constructor(
    private val networkDataSource: FilmNetworkDataSource
) : BaseRepositoryImpl(), FilmRepository {


    override suspend fun fetchEpisodes(): State<List<FilmModel>> =
        statefulBlock {
            // NOTE:
            // In case of receive more than 6 films, a 'paginator' system may be developed
            // with params 'next', 'previous' and 'count'. In this case, have been considered unnecessary
            // so just film model is returned
            if (FilmCacheDataSource.films.isNotEmpty()) {
                FilmCacheDataSource.films
            } else {
                networkDataSource.fetchFilms().results?.also { FilmCacheDataSource.insertFilms(it) }
                    ?: emptyList()
            }.map(FilmMapper::fromFilmResponseToModel).sortedWith(compareBy { it.episodeNum })
        }
}
