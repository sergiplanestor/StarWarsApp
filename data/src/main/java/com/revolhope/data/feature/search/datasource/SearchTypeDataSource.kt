package com.revolhope.data.feature.searchtype.datasource

import com.revolhope.data.feature.searchtype.response.SearchTypeResponse


interface SearchTypeDataSource {

    suspend fun fetchSearchType(): SearchTypeResponse?

    suspend fun updateSearchType(type: SearchTypeResponse): Boolean
}
