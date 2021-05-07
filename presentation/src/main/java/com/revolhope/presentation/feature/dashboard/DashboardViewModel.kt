package com.revolhope.presentation.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.domain.feature.film.usecase.FetchFilmsUseCase
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.domain.feature.search.model.SearchTypeModel
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.domain.feature.search.usecase.FetchCharactersByIdsUseCase
import com.revolhope.domain.feature.search.usecase.FetchCharactersByNameUseCase
import com.revolhope.domain.feature.search.usecase.FetchPlanetsByNameUseCase
import com.revolhope.domain.feature.search.usecase.FetchSearchTypeUseCase
import com.revolhope.domain.feature.search.usecase.FetchSpeciesByNameUseCase
import com.revolhope.domain.feature.search.usecase.UpdateSearchTypeUseCase
import com.revolhope.presentation.library.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val fetchFilmsUseCase: FetchFilmsUseCase,
    private val fetchSearchTypeUseCase: FetchSearchTypeUseCase,
    private val updateSearchTypeUseCase: UpdateSearchTypeUseCase,
    private val fetchCharactersByNameUseCase: FetchCharactersByNameUseCase,
    private val fetchCharactersByIdsUseCase: FetchCharactersByIdsUseCase,
    private val fetchSpeciesByNameUseCase: FetchSpeciesByNameUseCase,
    private val fetchPlanetsByNameUseCase: FetchPlanetsByNameUseCase
) : BaseViewModel() {

    private lateinit var originalFilms: List<FilmModel>

    private val _filmsLiveData = MutableLiveData<List<FilmModel>>()
    val filmsLiveData: LiveData<List<FilmModel>> get() = _filmsLiveData

    private val _searchTypeLiveData = MutableLiveData<SearchTypeModel?>()
    val searchTypeLiveData: LiveData<SearchTypeModel?> get() = _searchTypeLiveData

    private val _charactersByIdsLiveData = MutableLiveData<List<CharacterModel>>()
    val charactersByIdsLiveData: LiveData<List<CharacterModel>> get() = _charactersByIdsLiveData

    private val _charactersByNameLiveData = MutableLiveData<List<CharacterModel>>()
    val charactersByNameLiveData: LiveData<List<CharacterModel>> get() = _charactersByNameLiveData

    private val _speciesByNameLiveData = MutableLiveData<List<SpecieModel>>()
    val speciesByNameLiveData: LiveData<List<SpecieModel>> get() = _speciesByNameLiveData

    private val _planetsByNameLiveData = MutableLiveData<List<PlanetModel>>()
    val planetsByNameLiveData: LiveData<List<PlanetModel>> get() = _planetsByNameLiveData

    fun fetchFilms() {
        launchAsync(
            asyncTask = fetchFilmsUseCase::invoke,
            onSuccess = {
                originalFilms = it
                _filmsLiveData.value = it
            }
        )
    }

    fun fetchSearchType() {
        launchAsync(
            asyncTask = fetchSearchTypeUseCase::invoke,
            onSuccess = _searchTypeLiveData::setValue
        )
    }

    fun updateSearchType(type: SearchTypeModel) {
        launchAsync(
            asyncTask = { updateSearchTypeUseCase.invoke(UpdateSearchTypeUseCase.Request(type)) }
        )
    }

    fun applyFilmFilter(query: String) {
        if (::originalFilms.isInitialized) {
            _filmsLiveData.value = if (query.isBlank()) {
                originalFilms
            } else {
                originalFilms.filter { it.title.contains(query, ignoreCase = true) }
            }
        }
    }

    fun fetchCharactersByName(query: String) {
        launchAsync(
            asyncTask = { fetchCharactersByNameUseCase.invoke(FetchCharactersByNameUseCase.Params(query)) },
            onSuccess = _charactersByNameLiveData::setValue
        )
    }

    fun fetchCharactersByIds(ids: List<Int>) {
        launchAsync(
            asyncTask = { fetchCharactersByIdsUseCase.invoke(FetchCharactersByIdsUseCase.Params(ids)) },
            onSuccess = _charactersByIdsLiveData::setValue
        )
    }

    fun fetchSpeciesByName(query: String) {
        launchAsync(
            asyncTask = { fetchSpeciesByNameUseCase.invoke(FetchSpeciesByNameUseCase.Params(query)) },
            onSuccess = _speciesByNameLiveData::setValue
        )
    }

    fun fetchPlanetsByName(query: String) {
        launchAsync(
            asyncTask = { fetchPlanetsByNameUseCase.invoke(FetchPlanetsByNameUseCase.Params(query)) },
            onSuccess = _planetsByNameLiveData::setValue
        )
    }

}
