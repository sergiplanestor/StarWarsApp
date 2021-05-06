package com.revolhope.presentation.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.domain.feature.film.usecase.FetchFilmsUseCase
import com.revolhope.domain.feature.searchtype.model.SearchTypeModel
import com.revolhope.domain.feature.searchtype.usecase.FetchSearchTypeUseCase
import com.revolhope.domain.feature.searchtype.usecase.UpdateSearchTypeUseCase
import com.revolhope.presentation.library.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val fetchFilmsUseCase: FetchFilmsUseCase,
    private val fetchSearchTypeUseCase: FetchSearchTypeUseCase,
    private val updateSearchTypeUseCase: UpdateSearchTypeUseCase
) : BaseViewModel() {

    private val _filmsLiveData = MutableLiveData<List<FilmModel>>()
    val filmsLiveData: LiveData<List<FilmModel>> get() = _filmsLiveData

    private val _searchTypeLiveData = MutableLiveData<SearchTypeModel?>()
    val searchTypeLiveData: LiveData<SearchTypeModel?> get() = _searchTypeLiveData

    fun fetchFilms() {
        launchAsync(
            asyncTask = fetchFilmsUseCase::invoke,
            onSuccess = _filmsLiveData::setValue
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
}
