package com.revolhope.data.injection

import com.revolhope.data.feature.film.repositoryimpl.FilmRepositoryImpl
import com.revolhope.domain.feature.film.repository.FilmRepository
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

}
