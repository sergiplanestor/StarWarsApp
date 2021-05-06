package com.revolhope.presentation.feature.dashboard.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolhope.domain.feature.film.model.FilmModel
import com.revolhope.presentation.library.base.adapter.DiffUtilAdapter

class FilmAdapter(
    override val items: MutableList<FilmModel>,
    private val onItemClick: (item: FilmModel) -> Unit
) : DiffUtilAdapter<FilmModel, FilmView>(items) {

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): FilmView =
        FilmView(parent.context).apply {
            layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT,
            )
        }

    override fun onBindView(view: FilmView, position: Int) {
        with(items[position]) {
            view.bind(model = this)
            view.setOnClickListener { onItemClick.invoke(this) }
        }
    }

    override fun areItemsTheSame(oldItem: FilmModel, newItem: FilmModel): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: FilmModel, newItem: FilmModel): Boolean =
        oldItem.episodeNum == newItem.episodeNum
}
