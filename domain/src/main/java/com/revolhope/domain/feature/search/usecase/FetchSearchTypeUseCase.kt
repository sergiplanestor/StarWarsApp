package com.revolhope.domain.feature.search.usecase

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.SearchTypeModel
import com.revolhope.domain.feature.search.repository.SearchRepository
import javax.inject.Inject

class FetchSearchTypeUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(): State<SearchTypeModel?> =
        searchRepository.fetchSearchType()
}
