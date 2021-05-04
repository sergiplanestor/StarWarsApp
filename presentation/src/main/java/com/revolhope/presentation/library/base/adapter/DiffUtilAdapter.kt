package com.revolhope.presentation.library.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class DiffUtilAdapter<T, V : View>(open val items: MutableList<T>) :
    RecyclerView.Adapter<ViewWrapper<V>>() {

    // Abstract methods

    abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V

    abstract fun onBindView(view: V, position: Int)

    abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

    // RecyclerView.Adapter methods

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<V> =
        ViewWrapper(onCreateItemView(parent, viewType))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewWrapper<V>, position: Int) =
        onBindView(holder.view, position)

    // Public (and overridable) methods

    open fun update(newData: List<T>) {
        if (newData.isEmpty()) {
            items.clear()
            notifyDataSetChanged()
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                val diffResult = calculateDiffResult(newData)
                items.clear()
                items.addAll(newData)
                diffResult.dispatchUpdatesTo(this@DiffUtilAdapter)
            }
        }
    }

    // DiffUtil methods

    private suspend fun calculateDiffResult(newData: List<T>): DiffUtil.DiffResult =
        withContext(Dispatchers.IO) {
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    if (oldItemPosition <= items.lastIndex && newItemPosition <= newData.lastIndex) {
                        return areItemsTheSame(items[oldItemPosition], newData[newItemPosition])
                    }
                    return false
                }

                override fun getOldListSize() = items.size

                override fun getNewListSize() = newData.size

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    if (oldItemPosition <= items.lastIndex && newItemPosition <= newData.lastIndex) {
                        return areContentsTheSame(items[oldItemPosition], newData[newItemPosition])
                    }
                    return false
                }

                override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                    return getAdapterChangePayload(oldItemPosition, newItemPosition)
                }
            })
        }

    protected open fun getAdapterChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return null
    }
}
