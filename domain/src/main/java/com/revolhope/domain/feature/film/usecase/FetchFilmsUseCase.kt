package com.revolhope.domain.feature.film.usecase

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.domain.feature.film.repository.FilmRepository
import javax.inject.Inject

class FetchFilmsUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    suspend operator fun invoke(): State<List<FilmModel>> =
        filmRepository.fetchEpisodes()
}
