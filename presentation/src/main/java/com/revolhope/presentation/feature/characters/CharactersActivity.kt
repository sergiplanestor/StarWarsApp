package com.revolhope.presentation.feature.characters

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ActivityCharactersBinding
import com.revolhope.presentation.feature.characters.adapter.CharactersAdapter
import com.revolhope.presentation.library.base.BaseActivity
import com.revolhope.presentation.library.extensions.observe
import com.revolhope.presentation.library.extensions.toArrayList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersActivity : BaseActivity() {

    companion object {
        private const val EXTRA_CHARACTERS = "charactersActivity.extras.characters"
        private const val EXTRA_IS_MODAL = "charactersActivity.extras.is_modal"

        fun start(activity: Activity, characters: List<CharacterModel>, isModal: Boolean) {
            activity.startActivity(Intent(activity, CharactersActivity::class.java).apply {
                putExtras(
                    bundleOf(
                        EXTRA_NAVIGATION_TRANSITION to if (isModal) NavTransition.MODAL else NavTransition.LATERAL,
                        EXTRA_CHARACTERS to characters.toArrayList(),
                        EXTRA_IS_MODAL to isModal
                    )
                )
            })
        }
    }

    private val characters: List<CharacterModel> by lazy {
        intent.extras?.getParcelableArrayList(EXTRA_CHARACTERS) ?: emptyList()
    }
    private val isModal: Boolean by lazy {
        intent.extras?.getBoolean(EXTRA_IS_MODAL) ?: false
    }

    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var adapter: CharactersAdapter
    private lateinit var binding: ActivityCharactersBinding

    override fun inflateView(): View =
        ActivityCharactersBinding.inflate(layoutInflater).also { binding = it }.root

    override fun bindViews() {
        super.bindViews()
        setupToolbar()
        binding.charactersRecyclerView.adapter =
            CharactersAdapter(characters.toMutableList()).also { adapter = it }
    }

    override fun initObservers() {
        super.initObservers()
        observe(viewModel.errorLiveData) { onShowFeedback(it, isPositive = false) }
        observe(viewModel.errorResLiveData) { onShowFeedback(getString(it), isPositive = false) }
        observe(viewModel.homeWorldsLiveData, ::onHomeWorldsReceived)
    }

    override fun onLoadData() {
        super.onLoadData()
        viewModel.fetchHomeWorlds(characters.map { it.homeWorldId })
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (isModal) supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
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
