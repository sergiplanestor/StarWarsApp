package com.revolhope.presentation.library.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LifecycleOwner.observe(data: LiveData<T>, crossinline action: (T) -> Unit) =
    data.observe(this, { action.invoke(it) })
