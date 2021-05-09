package com.revolhope.data.common.extensions

import com.revolhope.domain.common.extensions.SLASH
import com.revolhope.domain.common.extensions.remove

/**
 * Given an URL e.g: https://swapi.dev/api/films/1/, this method return the last path segment parsed to
 * Int.
 */
fun String?.lastUrlSegmentAsInt(): Int? =
    if (this?.endsWith(SLASH) == true) {
        substring(0 until lastIndex)
    } else {
        this
    }?.split(SLASH).run { this?.get(lastIndex)?.remove(SLASH)?.toIntOrNull() }
