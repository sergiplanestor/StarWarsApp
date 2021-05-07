package com.revolhope.domain.feature.searchtype.usecase

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.searchtype.model.SearchTypeModel
import com.revolhope.domain.feature.searchtype.repository.SearchTypeRepository
import javax.inject.Inject

class UpdateSearchTypeUseCase @Inject constructor(
    private val searchTypeRepository: SearchTypeRepository
) {
    suspend operator fun invoke(request: Request): State<Boolean> =
        searchTypeRepository.updateSearchType(request.data)

    data class Request(val data: SearchTypeModel)
}
