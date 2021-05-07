package com.revolhope.data.feature.search.repositoryimpl

import com.revolhope.data.common.base.BaseRepositoryImpl
import com.revolhope.data.feature.search.datasource.SearchNetworkDataSource
import com.revolhope.data.feature.search.datasource.SearchTypeDataSource
import com.revolhope.data.feature.search.mapper.SearchTypeMapper
import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.domain.feature.search.model.SearchTypeModel
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.domain.feature.search.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val typeDataSource: SearchTypeDataSource,
    //private val searchNetworkDataSource: SearchNetworkDataSource
) : BaseRepositoryImpl(), SearchRepository {

    override suspend fun fetchSearchType(): State<SearchTypeModel?> =
        statefulBlock {
            typeDataSource.fetchSearchType()?.let(SearchTypeMapper::fromSearchTypeResponseToModel)
        }

    override suspend fun updateSearchType(searchType: SearchTypeModel): State<Boolean> =
        statefulBlock {
            typeDataSource.updateSearchType(searchType.let(SearchTypeMapper::fromSearchTypeModelToResponse))
        }

}
