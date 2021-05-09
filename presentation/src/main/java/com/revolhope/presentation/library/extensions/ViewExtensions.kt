package com.revolhope.presentation.library.extensions

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.animation.addListener
import androidx.core.view.children
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

const val PROPERTY_NAME_ALPHA = "alpha"
const val ALPHA_ANIM_DURATION = 300L

inline fun View.alphaAnim(
    isShowing: Boolean,
    duration: Long = ALPHA_ANIM_DURATION,
    interpolator: TimeInterpolator = AccelerateInterpolator(),
    startImmediately: Boolean = true,
    changeViewVisibility: Boolean = true,
    crossinline onStart: () -> Unit = {},
    crossinline onEnd: () -> Unit = {}
): ObjectAnimator = ObjectAnimator.ofFloat(
    this,
    PROPERTY_NAME_ALPHA,
    if (isShowing) 0f else 1f,
    if (isShowing) 1f else 0f
).apply {
    this.duration = duration
    this.interpolator = interpolator
    this.addListener(
        onStart = {
            onStart.invoke()
            if (changeViewVisibility && isShowing) isVisible = true
        },
        onEnd = {
            onEnd.invoke()
            if (changeViewVisibility && !isShowing) isVisible = false
        }
    )
    if (startImmediately) start()
}

inline val View?.decorView: FrameLayout?
    get() {
        var view = this
        do {
            if (view is FrameLayout && view.id == android.R.id.content) {
                return view
            } else {
                view = view?.parent as? View
            }
        } while (view != null)
        return null
    }

inline val View?.snackBarSuitableParent: ViewGroup?
    get() = decorView?.children?.findLast { it is CoordinatorLayout } as? CoordinatorLayout ?: decorView


inline val TextInputEditText.input: String? get() = text?.toString()

inline val TextInputLayout.errorLayout: LinearLayout? get() = children.firstOrNull { it is LinearLayout } as? LinearLayout

fun View.drawable(@DrawableRes res: Int): Drawable? = context.drawable(res)

fun View.color(@ColorRes res: Int): Int = context.color(res)


