package com.revolhope.domain.feature.searchtype.repository

import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.searchtype.model.SearchTypeModel

interface SearchTypeRepository {

    suspend fun fetchSearchType(): State<SearchTypeModel?>

    suspend fun updateSearchType(searchType: SearchTypeModel): State<Boolean>
}
