package com.revolhope.data.feature.film.datasource

import com.revolhope.data.feature.film.response.FilmResponse

/**
 * Cache data source for [FilmResponse].
 */
object FilmCacheDataSource {

    // Private attributes
    private val _films: MutableList<FilmResponse> = mutableListOf()
    val films: List<FilmResponse> get() = _films

    // Public methods
    fun insertFilms(items: List<FilmResponse>) {
        _films.addAll(items)
    }

    fun clearCache() {
        _films.clear()
    }
}
