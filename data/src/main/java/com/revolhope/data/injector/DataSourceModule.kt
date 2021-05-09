package com.revolhope.data.injector

import com.revolhope.data.common.local.LocalStorageDataSource
import com.revolhope.data.feature.search.datasource.SearchTypeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindSearchTypeDataSource(
        localStorageDataSource: LocalStorageDataSource
    ): SearchTypeDataSource

}
