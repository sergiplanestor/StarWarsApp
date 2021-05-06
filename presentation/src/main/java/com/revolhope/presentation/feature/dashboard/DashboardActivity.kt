package com.revolhope.presentation.feature.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.domain.feature.searchtype.model.SearchTypeModel
import com.revolhope.mylibra.databinding.ActivityDashboardBinding
import com.revolhope.presentation.feature.dashboard.adapter.FilmAdapter
import com.revolhope.presentation.library.base.BaseActivity
import com.revolhope.presentation.library.components.searchview.SearchView
import com.revolhope.presentation.library.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override val binding: ActivityDashboardBinding
        get() = ActivityDashboardBinding.inflate(layoutInflater)

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: FilmAdapter

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, DashboardActivity::class.java).apply {
                putExtras(bundleOf(EXTRA_NAVIGATION_TRANSITION to NavTransition.LATERAL))
            })
        }
    }

    override fun bindViews() {
        super.bindViews()
        bindSearchView()
        bindRecycler(emptyList())
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.filmsLiveData, ::onFilmsReceived)
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
    }

    private fun onFilmClick(film: FilmModel) {

    }

    private fun onQuerySubmit(type: SearchTypeModel, text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
