package com.revolhope.presentation.feature.species.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolhope.domain.feature.search.model.SpecieModel
import com.revolhope.presentation.library.base.adapter.DiffUtilAdapter

class SpeciesAdapter(override val items: MutableList<SpecieModel>) :
    DiffUtilAdapter<SpecieModel, SpecieView>(items) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): SpecieView =
        SpecieView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT,
            )
        }

    override fun onBindView(view: SpecieView, position: Int) {
        with(items[position]) {
            view.bind(model = this)
        }
    }

    override fun areItemsTheSame(oldItem: SpecieModel, newItem: SpecieModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: SpecieModel, newItem: SpecieModel): Boolean =
        oldItem.name == newItem.name
}
