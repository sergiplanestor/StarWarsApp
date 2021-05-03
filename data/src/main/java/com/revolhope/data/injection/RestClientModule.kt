package com.revolhope.data.injection

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.revolhope.data.BuildConfig
import com.revolhope.data.feature.film.datasource.StarWarsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestClientModule {

    @Singleton
    @Provides
    fun providesOkhttpClient() : OkHttpClient =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor())
            }
        }.build()

    @Singleton
    @Provides
    fun providesApi(client: OkHttpClient): StarWarsApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.STAR_WARS_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
            .create(StarWarsApi::class.java)

}
