package com.revolhope.data.common.local

import android.content.SharedPreferences
import com.revolhope.data.feature.search.datasource.SearchTypeDataSource
import com.revolhope.data.feature.search.response.SearchTypeResponse
import javax.inject.Inject

/**
 * Shared preferences data source. It implements [SearchTypeDataSource] interface.
 */
class LocalStorageDataSource @Inject constructor(private val preferences: SharedPreferences) :
    SearchTypeDataSource {

    private companion object {
        private const val SEARCH_TYPE = "starwars.prefs.search.type"
    }

    override suspend fun fetchSearchType(): SearchTypeResponse? =
        preferences.getInt(SEARCH_TYPE, -1).takeIf { it != -1 }?.let { SearchTypeResponse(it) }

    override suspend fun updateSearchType(type: SearchTypeResponse): Boolean {
        preferences.edit().apply {
            putInt(SEARCH_TYPE, type.id)
            apply()
        }
        return true
    }
}
