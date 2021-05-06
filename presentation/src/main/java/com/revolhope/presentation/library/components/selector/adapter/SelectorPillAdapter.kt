package com.revolhope.presentation.library.components.selector.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolhope.presentation.library.components.selector.adapter.pill.SelectorPillUIModel
import com.revolhope.presentation.library.components.selector.adapter.pill.SelectorPillView

class SelectorPillAdapter(
    private val items: List<SelectorPillUIModel>,
    private val onPillSelected: (id: Int) -> Unit
) : RecyclerView.Adapter<SelectorPillAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(SelectorPillView(parent.context))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(items[position]) {
            holder.pillView.bind(this)
            holder.pillView.setOnClickListener { onPillSelected.invoke(id) }
        }
    }

    override fun getItemCount(): Int = items.count()

    data class Holder(val pillView: SelectorPillView) : RecyclerView.ViewHolder(pillView)
}
