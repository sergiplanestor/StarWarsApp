package com.revolhope.presentation.feature.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.domain.feature.film.usecase.FetchFilmsUseCase
import com.revolhope.presentation.library.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val fetchFilmsUseCase: FetchFilmsUseCase
) : BaseViewModel() {

    private val _filmsLivaData = MutableLiveData<List<FilmModel>>()
    val filmsLiveData: LiveData<List<FilmModel>> get() = _filmsLivaData

    fun fetchFilms() {
        launchAsync(
            asyncTask = fetchFilmsUseCase::invoke,
            onSuccess = _filmsLivaData::setValue
        )
    }
}
