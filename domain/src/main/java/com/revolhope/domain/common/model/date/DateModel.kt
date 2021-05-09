package com.revolhope.domain.common.model.date

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DateModel(
    val millis: Long,
    val formatted: String
): Parcelable {

    companion object {
        val empty: DateModel get() =
            DateModel(
                millis = Long.MIN_VALUE,
                formatted = ""
            )
    }

}
