package com.revolhope.data.feature.search.datasource

import com.revolhope.data.feature.search.response.SearchTypeResponse


interface SearchTypeDataSource {

    suspend fun fetchSearchType(): SearchTypeResponse?

    suspend fun updateSearchType(type: SearchTypeResponse): Boolean
}
