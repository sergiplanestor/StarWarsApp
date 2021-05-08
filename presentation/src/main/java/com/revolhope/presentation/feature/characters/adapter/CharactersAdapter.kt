package com.revolhope.presentation.feature.characters.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolhope.domain.feature.search.model.CharacterModel
import com.revolhope.presentation.library.base.adapter.DiffUtilAdapter

class CharactersAdapter(override val items: MutableList<CharacterModel>) :
    DiffUtilAdapter<CharacterModel, CharacterView>(items) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): CharacterView =
        CharacterView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT,
            )
        }

    override fun onBindView(view: CharacterView, position: Int) {
        with(items[position]) {
            view.bind(model = this)
        }
    }

    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean =
        oldItem.name == newItem.name
}
