package com.revolhope.presentation.library.extensions

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

inline val Context.layoutInflater: LayoutInflater get() = LayoutInflater.from(this)

fun Context.drawable(@DrawableRes drawableId: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableId)

fun Context.color(@ColorRes colorId: Int): Int = ContextCompat.getColor(this, colorId)

fun Context.hideKeyboard(view: View?) {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(view?.windowToken, 0)
}
