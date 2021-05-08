package com.revolhope.presentation.library.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Data class that extends [RecyclerView.ViewHolder] to be used on [DiffUtilAdapter].
 */
data class ViewWrapper<out V : View>(val view: V) : RecyclerView.ViewHolder(view)
