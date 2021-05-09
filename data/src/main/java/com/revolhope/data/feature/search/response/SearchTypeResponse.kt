package com.revolhope.data.feature.search.response

import com.google.gson.annotations.SerializedName

/**
 * This object is so simple so should be unwrapped out and be stored just as Int.
 * It has been left for example purposes.
 */
data class SearchTypeResponse(@SerializedName("id") val id: Int)
