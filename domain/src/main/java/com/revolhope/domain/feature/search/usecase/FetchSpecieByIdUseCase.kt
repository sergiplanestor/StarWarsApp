package com.revolhope.domain.feature.search.usecase

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.domain.feature.search.repository.SearchRepository
import javax.inject.Inject

class FetchSpecieByIdUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(params: Params): State<SpecieModel> =
        searchRepository.fetchSpecieById(params.id)

    data class Params(val id: Int)
}
