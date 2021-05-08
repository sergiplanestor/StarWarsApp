package com.revolhope.presentation.library.extensions

import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.TypedValue
import java.util.*
import kotlin.collections.ArrayList

inline val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()

inline val String.lowerCase: String
    get() = toLowerCase(Locale.getDefault())

inline val String.upperCase: String
    get() = toLowerCase(Locale.getDefault())

fun String.toBold(start: Int = 0, end: Int = this.length): Spannable =
    SpannableString(this).also {
        it.setSpan(
            StyleSpan(Typeface.BOLD),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

// List is not primitive but has considered appropriate implement this method in this file.
fun <T> List<T>.toArrayList(): ArrayList<T> = ArrayList(this)
