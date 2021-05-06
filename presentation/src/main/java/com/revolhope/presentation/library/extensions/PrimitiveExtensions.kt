package com.revolhope.presentation.library.extensions

import android.content.res.Resources
import android.util.TypedValue
import java.util.*

// -------------------------------------------------------------------------------------------------
// Int
// -------------------------------------------------------------------------------------------------

inline val Int.dp: Int
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    ).toInt()

inline val String.lowerCase: String
    get() = toLowerCase(Locale.getDefault())

inline val String.upperCase: String
    get() = toLowerCase(Locale.getDefault())
