package com.revolhope.presentation.feature.species

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ActivitySpeciesBinding
import com.revolhope.presentation.feature.species.adapter.SpeciesAdapter
import com.revolhope.presentation.library.base.BaseActivity
import com.revolhope.presentation.library.extensions.toArrayList

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
    private lateinit var binding: ActivitySpeciesBinding

    override fun inflateView(): View =
        ActivitySpeciesBinding.inflate(layoutInflater).also { binding = it }.root

    override fun bindViews() {
        super.bindViews()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        binding.speciesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.speciesRecyclerView.adapter = SpeciesAdapter(species.toMutableList())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
}
