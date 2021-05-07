package com.revolhope.data.feature.search.mapper

import com.revolhope.data.feature.search.response.SearchTypeResponse
import com.revolhope.domain.feature.search.model.SearchTypeModel

object SearchTypeMapper {

    fun fromSearchTypeModelToResponse(model: SearchTypeModel): SearchTypeResponse =
        SearchTypeResponse(model.id)

    fun fromSearchTypeResponseToModel(response: SearchTypeResponse): SearchTypeModel =
        SearchTypeModel.fromId(response.id)
}
