package com.revolhope.presentation.feature.species

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revolhope.domain.feature.search.usecase.FetchPlanetsByIdsUseCase
import com.revolhope.presentation.library.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpeciesViewModel @Inject constructor(
    private val fetchPlanetByIdsUseCase: FetchPlanetsByIdsUseCase
) : BaseViewModel() {

    private val _homeWorldsLiveData = MutableLiveData<List<String>>()
    val homeWorldsLiveData : LiveData<List<String>> = _homeWorldsLiveData

    fun fetchHomeWorlds(ids: List<Int>) {
        launchAsync(
            asyncTask = { fetchPlanetByIdsUseCase.invoke(FetchPlanetsByIdsUseCase.Params(ids)) },
            onSuccess = { planets -> _homeWorldsLiveData.value = planets.map { it.name } }
        )
    }

}
