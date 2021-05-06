package com.revolhope.presentation.library.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

data class ViewWrapper<out V : View>(val view: V) : RecyclerView.ViewHolder(view)
