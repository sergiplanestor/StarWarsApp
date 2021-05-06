package com.revolhope.presentation.library.components.selector.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.revolhope.presentation.library.extensions.dp

class SelectorPillItemDecorator : RecyclerView.ItemDecoration() {

    companion object {
        private const val MARGIN_EXTREMES = 16
        private const val MARGIN_END = 12
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.left = MARGIN_EXTREMES.dp
                outRect.right = MARGIN_END.dp
            }
            parent.adapter?.itemCount?.minus(1) -> {
                outRect.right = MARGIN_EXTREMES.dp
            }
            else -> {
                outRect.right = MARGIN_END.dp
            }
        }
    }

}
