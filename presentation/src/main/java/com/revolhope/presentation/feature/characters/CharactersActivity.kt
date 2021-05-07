package com.revolhope.presentation.feature.characters

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.mylibra.R
import com.revolhope.mylibra.databinding.ActivityCharactersBinding
import com.revolhope.presentation.feature.characters.adapter.CharactersAdapter
import com.revolhope.presentation.library.base.BaseActivity
import com.revolhope.presentation.library.extensions.toArrayList

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

    private lateinit var binding: ActivityCharactersBinding

    override fun inflateView(): View =
        ActivityCharactersBinding.inflate(layoutInflater).also { binding = it }.root

    override fun bindViews() {
        super.bindViews()
        setupToolbar()
        binding.charactersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.charactersRecyclerView.adapter = CharactersAdapter(characters.toMutableList())
    }

    override fun initObservers() {
        super.initObservers()
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
}
