package com.revolhope.domain.feature.search.usecase

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.SearchTypeModel
import com.revolhope.domain.feature.search.repository.SearchRepository
import javax.inject.Inject

class UpdateSearchTypeUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(request: Request): State<Boolean> =
        searchRepository.updateSearchType(request.data)

    data class Request(val data: SearchTypeModel)
}
