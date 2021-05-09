package com.revolhope.presentation.feature.species

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ActivitySpeciesBinding
import com.revolhope.presentation.feature.species.adapter.SpeciesAdapter
import com.revolhope.presentation.library.base.BaseActivity
import com.revolhope.presentation.library.extensions.observe
import com.revolhope.presentation.library.extensions.toArrayList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpeciesActivity : BaseActivity() {

    companion object {

        private const val EXTRA_SPECIES = "speciesActivity.extras.species"

        fun start(activity: Activity, species: List<SpecieModel>) {
            activity.startActivity(Intent(activity, SpeciesActivity::class.java).apply {
                putExtras(
                    bundleOf(
                        EXTRA_NAVIGATION_TRANSITION to NavTransition.MODAL,
                        EXTRA_SPECIES to species.toArrayList()
                    )
                )
            })
        }
    }

    private val species: List<SpecieModel> by lazy {
        intent.extras?.getParcelableArrayList(EXTRA_SPECIES) ?: emptyList()
    }
    private val viewModel: SpeciesViewModel by viewModels()
    private lateinit var adapter: SpeciesAdapter
    private lateinit var binding: ActivitySpeciesBinding

    override fun inflateView(): View =
        ActivitySpeciesBinding.inflate(layoutInflater).also { binding = it }.root

    override fun bindViews() {
        super.bindViews()
        setupToolbar()
        setupEmptyState()
        binding.speciesRecyclerView.adapter = SpeciesAdapter(species.toMutableList()).also { adapter = it }
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.errorLiveData) { onShowFeedback(it, isPositive = false) }
        observe(viewModel.errorResLiveData) { onShowFeedback(getString(it), isPositive = false) }
        observe(viewModel.homeWorldsLiveData, ::onHomeWorldsReceived)
    }

    override fun onLoadData() {
        super.onLoadData()
        viewModel.fetchHomeWorlds(species.map { it.homeWorldId })
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
    }

    private fun setupEmptyState() {
        binding.speciesRecyclerView.isVisible = species.isNotEmpty()
        binding.emptyStateView.isVisible = species.isEmpty()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }

    private fun onHomeWorldsReceived(homeWorlds: List<String>) {
        if (::adapter.isInitialized) {
            adapter.apply {
                items.forEachIndexed { index, item ->
                    item.homeWorld = homeWorlds.getOrNull(index)
                }
                notifyDataSetChanged()
            }
        }
    }
}
