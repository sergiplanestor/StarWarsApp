package com.revolhope.domain.feature.search.usecase

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.domain.feature.search.repository.SearchRepository
import javax.inject.Inject

class FetchPlanetsByIdsUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(params: Params): State<List<PlanetModel>> =
        searchRepository.fetchPlanetsByIds(params.ids)

    data class Params(val ids: List<Int>)
}
