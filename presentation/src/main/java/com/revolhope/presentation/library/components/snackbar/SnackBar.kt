package com.revolhope.presentation.library.components.snackbar

import android.annotation.SuppressLint
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.revolhope.presentation.library.components.snackbar.model.SnackBarModel
import com.revolhope.presentation.library.components.snackbar.view.SnackBarErrorView
import com.revolhope.presentation.library.components.snackbar.view.SnackBarSuccessView
import com.revolhope.presentation.library.components.snackbar.view.SnackBarView
import com.revolhope.presentation.library.extensions.dp
import com.revolhope.presentation.library.extensions.snackBarSuitableParent

class SnackBar(
    parent: ViewGroup,
    content: SnackBarView<*>
) : BaseTransientBottomBar<SnackBar>(parent, content, content) {

    init {
        getView().apply {
            setBackgroundColor(context.getColor(android.R.color.transparent))
            setPadding(0, 0, 0, PADDING_BOTTOM_DP.dp)
        }
    }

    companion object {

        private const val PADDING_BOTTOM_DP = 20
        private const val DURATION = 3000

        @SuppressLint("WrongConstant")
        fun show(view: View?, model: SnackBarModel) {

            val viewGroup = view.snackBarSuitableParent ?: return

            val content: SnackBarView<*> = when (model) {
                is SnackBarModel.Success -> {
                    SnackBarSuccessView(viewGroup.context).apply { bind(model) }
                }
                is SnackBarModel.Error -> {
                    SnackBarErrorView(viewGroup.context).apply { bind(model) }
                }
            }

            SnackBar(viewGroup, content).apply {
                duration = DURATION
                content.setOnClickListener {
                    model.onClick?.invoke()
                    dismiss()
                }
            }.show()
            TransitionManager.beginDelayedTransition(viewGroup)
        }
    }
}
