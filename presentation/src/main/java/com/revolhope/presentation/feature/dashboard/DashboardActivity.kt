package com.revolhope.presentation.feature.dashboard

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.domain.feature.search.model.SearchTypeModel
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ActivityDashboardBinding
import com.revolhope.presentation.feature.characters.CharactersActivity
import com.revolhope.presentation.feature.dashboard.adapter.FilmAdapter
import com.revolhope.presentation.feature.planets.PlanetsActivity
import com.revolhope.presentation.feature.species.SpeciesActivity
import com.revolhope.presentation.library.base.BaseActivity
import com.revolhope.presentation.library.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: FilmAdapter

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, DashboardActivity::class.java).apply {
                putExtras(bundleOf(EXTRA_NAVIGATION_TRANSITION to NavTransition.LATERAL))
            })
        }
    }

    override fun inflateView(): View =
        ActivityDashboardBinding.inflate(layoutInflater).also { binding = it }.root

    override fun bindViews() {
        super.bindViews()
        bindSearchView()
        bindRecycler(emptyList())
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.errorLiveData) {
            if (::adapter.isInitialized) adapter.isClickEnabled = true
            onShowFeedback(it, isPositive = false)
        }
        observe(viewModel.errorResLiveData) {
            if (::adapter.isInitialized) adapter.isClickEnabled = true
            onShowFeedback(getString(it), isPositive = false)
        }
        observe(viewModel.loaderLiveData, ::onShowLoader)
        observe(viewModel.filmsLiveData, ::onFilmsReceived)
        observe(viewModel.searchTypeLiveData, ::onSearchTypeReceived)
        observe(viewModel.charactersByNameLiveData, ::onCharactersByNameReceived)
        observe(viewModel.charactersByIdsLiveData, ::onCharactersByIdsReceived)
        observe(viewModel.speciesByNameLiveData, ::onSpeciesByNameReceived)
        observe(viewModel.planetsByNameLiveData, ::onPlanetsByNameReceived)
    }

    override fun onLoadData() {
        super.onLoadData()
        viewModel.fetchFilms()
        viewModel.fetchSearchType()
    }

    override fun onSaveChanges() {
        super.onSaveChanges()
        viewModel.updateSearchType(binding.searchView.searchType)
    }

    private fun bindSearchView() {
        binding.searchView.onQuerySummit = ::onQuerySubmit
    }

    private fun bindRecycler(films: List<FilmModel>) {
        binding.filmsRecyclerView.adapter =
            FilmAdapter(films.toMutableList(), ::onFilmClick).also { adapter = it }
    }

    private fun onFilmsReceived(films: List<FilmModel>) {
        if (::adapter.isInitialized) {
            adapter.update(films)
        }
        setupEmptyState(films.isEmpty())
    }

    private fun setupEmptyState(show: Boolean) {
        binding.filmsRecyclerView.isVisible = show.not()
        binding.emptyStateView.isVisible = show
    }

    private fun onSearchTypeReceived(type: SearchTypeModel?) {
        type?.let { binding.searchView.searchType = it }
    }

    private fun onFilmClick(film: FilmModel) {
        adapter.isClickEnabled = false
        viewModel.fetchCharactersByIds(film.charactersIds)
    }

    private fun onCharactersByIdsReceived(characters: List<CharacterModel>) {
        adapter.isClickEnabled = true
        CharactersActivity.start(activity = this, characters = characters, isModal = false)
    }
    
    private fun onCharactersByNameReceived(characters: List<CharacterModel>) {
        CharactersActivity.start(activity = this, characters = characters, isModal = true)
    }

    private fun onSpeciesByNameReceived(species: List<SpecieModel>) {
        SpeciesActivity.start(activity = this, species = species)
    }

    private fun onPlanetsByNameReceived(planets: List<PlanetModel>) {
        PlanetsActivity.start(activity = this, planets = planets)
    }

    private fun onQuerySubmit(type: SearchTypeModel, text: String) {
        (type !is SearchTypeModel.Unknown).run {
            onShowFeedback(
                message = getString(if (this) R.string.processing_query else R.string.error_default),
                isPositive = this
            )
        }
        when (type) {
            // Filter current list
            is SearchTypeModel.Episode -> viewModel.applyFilmFilter(text)
            // Show results on a modal screen
            is SearchTypeModel.Characters -> {
                viewModel.fetchCharactersByName(text)
            }
            is SearchTypeModel.Planets -> {
                viewModel.fetchPlanetsByName(text)
            }
            is SearchTypeModel.Species -> {
                viewModel.fetchSpeciesByName(text)
            }
            // Unrecognized type, should never happen
            SearchTypeModel.Unknown -> { /* Nothing to do here */
            }
        }
    }
}
