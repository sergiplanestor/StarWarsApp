package com.revolhope.presentation.feature.planets

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ActivityPlanetsBinding
import com.revolhope.presentation.feature.planets.adapter.PlanetsAdapter
import com.revolhope.presentation.library.base.BaseActivity
import com.revolhope.presentation.library.extensions.observe
import com.revolhope.presentation.library.extensions.toArrayList
import dagger.hilt.android.AndroidEntryPoint

class PlanetsActivity : BaseActivity() {

    companion object {

        private const val EXTRA_PLANETS = "planetsActivity.extras.planets"

        fun start(activity: Activity, planets: List<PlanetModel>) {
            activity.startActivity(Intent(activity, PlanetsActivity::class.java).apply {
                putExtras(
                    bundleOf(
                        EXTRA_NAVIGATION_TRANSITION to NavTransition.MODAL,
                        EXTRA_PLANETS to planets.toArrayList()
                    )
                )
            })
        }
    }

    private val planets: List<PlanetModel> by lazy {
        intent.extras?.getParcelableArrayList(EXTRA_PLANETS) ?: emptyList()
    }
    private lateinit var binding: ActivityPlanetsBinding

    override fun inflateView(): View =
        ActivityPlanetsBinding.inflate(layoutInflater).also { binding = it }.root

    override fun bindViews() {
        super.bindViews()
        setupToolbar()
        setupEmptyState()
        binding.planetsRecyclerView.adapter = PlanetsAdapter(planets.toMutableList())
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
    }

    private fun setupEmptyState() {
        binding.planetsRecyclerView.isVisible = planets.isNotEmpty()
        binding.emptyStateView.isVisible = planets.isEmpty()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
}
