package com.revolhope.presentation.library.components.snackbar.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback
import com.revolhope.presentation.library.components.snackbar.model.SnackBarModel
import com.revolhope.presentation.library.extensions.alphaAnim

abstract class SnackBarView<T : SnackBarModel> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    protected var contentLayout: View? = null

    abstract fun bind(model: T)

    override fun animateContentOut(delay: Int, duration: Int) {
        animateContent(delay.toLong(), duration.toLong(), isShowing = false)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        animateContent(delay.toLong(), duration.toLong(), isShowing = true)
    }

    protected open fun animateContent(delay: Long, duration: Long, isShowing: Boolean) {
        contentLayout?.alphaAnim(
            isShowing = isShowing,
            duration = duration,
            startImmediately = false,
            changeViewVisibility = false
        )?.also { it.startDelay = delay }?.start()
    }
}
