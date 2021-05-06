package com.revolhope.data.injector

import com.revolhope.data.feature.film.repositoryimpl.FilmRepositoryImpl
import com.revolhope.data.feature.searchtype.repositoryimpl.SearchTypeRepositoryImpl
import com.revolhope.domain.feature.film.repository.FilmRepository
import com.revolhope.domain.feature.searchtype.repository.SearchTypeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFilmRepository(
        groceryRepositoryImpl: FilmRepositoryImpl
    ): FilmRepository

    @Binds
    abstract fun bindSearchTypeRepository(
        searchTypeRepositoryImpl: SearchTypeRepositoryImpl
    ): SearchTypeRepository
}
