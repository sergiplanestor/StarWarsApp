package com.revolhope.domain.feature.search.repository

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.SearchTypeModel

interface SearchTypeRepository {

    suspend fun fetchSearchType(): State<SearchTypeModel?>

    suspend fun updateSearchType(searchType: SearchTypeModel): State<Boolean>
}
