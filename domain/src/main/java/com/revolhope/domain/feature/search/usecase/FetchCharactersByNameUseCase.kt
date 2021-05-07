package com.revolhope.domain.feature.search.usecase

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.domain.feature.search.repository.SearchRepository
import javax.inject.Inject

class FetchCharactersByNameUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(params: Params): State<List<CharacterModel>> =
        searchRepository.fetchCharactersByName(params.query)

    data class Params(val query: String)
}
