package com.revolhope.data.feature.searchtype.repositoryimpl

import com.revolhope.data.common.base.BaseRepositoryImpl
import com.revolhope.data.common.local.LocalStorageDataSource
import com.revolhope.data.feature.searchtype.mapper.SearchTypeMapper
import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.searchtype.model.SearchTypeModel
import com.revolhope.domain.feature.searchtype.repository.SearchTypeRepository
import javax.inject.Inject

class SearchTypeRepositoryImpl @Inject constructor(
    private val dataSource: LocalStorageDataSource
) : BaseRepositoryImpl(), SearchTypeRepository {

    override suspend fun fetchSearchType(): State<SearchTypeModel?> =
        statefulBlock {
            dataSource.fetchSearchType()?.let(SearchTypeMapper::fromSearchTypeResponseToModel)
        }

    override suspend fun updateSearchType(searchType: SearchTypeModel): State<Boolean> =
        statefulBlock {
            dataSource.updateSearchType(searchType.let(SearchTypeMapper::fromSearchTypeModelToResponse))
        }
}
