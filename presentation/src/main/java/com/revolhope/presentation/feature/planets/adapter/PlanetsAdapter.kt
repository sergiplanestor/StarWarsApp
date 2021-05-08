package com.revolhope.presentation.feature.planets.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolhope.domain.feature.search.model.PlanetModel
import com.revolhope.presentation.library.base.adapter.DiffUtilAdapter

class PlanetsAdapter(override val items: MutableList<PlanetModel>) :
    DiffUtilAdapter<PlanetModel, PlanetView>(items) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): PlanetView =
        PlanetView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT,
            )
        }

    override fun onBindView(view: PlanetView, position: Int) {
        with(items[position]) {
            view.bind(model = this)
        }
    }

    override fun areItemsTheSame(oldItem: PlanetModel, newItem: PlanetModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: PlanetModel, newItem: PlanetModel): Boolean =
        oldItem.name == newItem.name
}
