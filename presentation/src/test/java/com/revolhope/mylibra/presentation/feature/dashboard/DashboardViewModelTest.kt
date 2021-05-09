package com.revolhope.mylibra.presentation.feature.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.revolhope.domain.common.model.net.State
import com.revolhope.domain.feature.film.usecase.FetchFilmsUseCase
import com.revolhope.domain.feature.search.usecase.FetchCharactersByIdsUseCase
import com.revolhope.domain.feature.search.usecase.FetchCharactersByNameUseCase
import com.revolhope.domain.feature.search.usecase.FetchPlanetsByNameUseCase
import com.revolhope.domain.feature.search.usecase.FetchSearchTypeUseCase
import com.revolhope.domain.feature.search.usecase.FetchSpeciesByNameUseCase
import com.revolhope.domain.feature.search.usecase.UpdateSearchTypeUseCase
import com.revolhope.mylibra.MainCoroutineRule
import com.revolhope.presentation.feature.dashboard.DashboardViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    @MockK
    lateinit var fetchFilmsUseCase: FetchFilmsUseCase

    @MockK
    lateinit var fetchSearchTypeUseCase: FetchSearchTypeUseCase

    @MockK
    lateinit var updateSearchTypeUseCase: UpdateSearchTypeUseCase

    @MockK
    lateinit var fetchCharactersByNameUseCase: FetchCharactersByNameUseCase

    @MockK
    lateinit var fetchCharactersByIdsUseCase: FetchCharactersByIdsUseCase

    @MockK
    lateinit var fetchSpeciesByNameUseCase: FetchSpeciesByNameUseCase

    @MockK
    lateinit var fetchPlanetsByNameUseCase: FetchPlanetsByNameUseCase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var dashboardViewModel: DashboardViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        dashboardViewModel = DashboardViewModel(
            fetchFilmsUseCase,
            fetchSearchTypeUseCase,
            updateSearchTypeUseCase,
            fetchCharactersByNameUseCase,
            fetchCharactersByIdsUseCase,
            fetchSpeciesByNameUseCase,
            fetchPlanetsByNameUseCase
        )
    }


    @Test
    fun `given loading state, when fetching any, then loaderLiveData active twice and final value is false`() {

        var isFirstCall = true
        var currentFirst = false

        // Given
        val mockedObserver = createDummyObserver<Boolean>(action = {
            if (isFirstCall) {
                currentFirst = this
                isFirstCall = false
            }
        })
        dashboardViewModel.loaderLiveData.observeForever(mockedObserver)

        coEvery { fetchFilmsUseCase.invoke() } returns State.Success(emptyList())

        // When
        dashboardViewModel.fetchFilms()

        // Then
        val slot = slot<Boolean>()
        coVerify(exactly = 2) { mockedObserver.onChanged(capture(slot)) }

        val expectedFirst = true
        val expectedSecond = false
        assertEquals(expectedFirst, currentFirst)
        assertEquals(expectedSecond, if (slot.isCaptured) slot.captured else expectedSecond.not())
    }

    @Test
    fun `given error state, when fetching any occurs an error, then errorLiveData value changes`() {

        // Given
        val mockedObserver = createDummyObserver<String>()
        dashboardViewModel.errorLiveData.observeForever(mockedObserver)

        coEvery { fetchFilmsUseCase.invoke() } returns State.Error("This is an error")

        // When
        dashboardViewModel.fetchFilms()

        // Then
        val slot = slot<String>()
        coVerify(exactly = 1) { mockedObserver.onChanged(capture(slot)) }
    }

    private fun <T> createDummyObserver(action: (T.() -> Unit) = { }): Observer<T> =
        spyk(Observer { action.invoke(it) })
}
