package com.revolhope.data.feature.searchtype.mapper

import com.revolhope.data.feature.searchtype.response.SearchTypeResponse
import com.revolhope.domain.feature.searchtype.model.SearchTypeModel

object SearchTypeMapper {

    fun fromSearchTypeModelToResponse(model: SearchTypeModel): SearchTypeResponse =
        SearchTypeResponse(model.id)

    fun fromSearchTypeResponseToModel(response: SearchTypeResponse): SearchTypeModel =
        SearchTypeModel.fromId(response.id)
}
