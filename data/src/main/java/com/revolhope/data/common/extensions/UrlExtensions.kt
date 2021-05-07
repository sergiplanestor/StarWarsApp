package com.revolhope.data.common.extensions

import com.revolhope.domain.common.extensions.SLASH
import com.revolhope.domain.common.extensions.remove

fun String?.lastUrlSegmentAsInt(): Int? =
    this?.split(SLASH).run { this?.get(lastIndex)?.remove(SLASH)?.toIntOrNull() }
