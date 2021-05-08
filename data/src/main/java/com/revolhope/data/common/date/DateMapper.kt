package com.revolhope.data.common.date

import com.google.gson.internal.bind.util.ISO8601Utils
import com.revolhope.domain.common.extensions.blockOrNull
import com.revolhope.domain.common.extensions.letOr
import com.revolhope.domain.common.model.date.DateModel
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Mapper to parse from String to DateModel
 */
object DateMapper {

    // Private const
    private const val DATE_INVERTED_PATTERN = "yyyy-MM-dd"
    private const val DEFAULT_DATE_PATTERN = "dd/MM/yyyy"

    // Private methods
    private fun sdf(pattern: String = DEFAULT_DATE_PATTERN): SimpleDateFormat =
        SimpleDateFormat(pattern, Locale.getDefault())

    private fun format(value: Long?, pattern: String = DEFAULT_DATE_PATTERN): String? =
        blockOrNull { sdf(pattern).format(value) }

    private fun String?.toDateMillis(pattern: String = DATE_INVERTED_PATTERN): Long? =
        blockOrNull { this?.let { sdf(pattern).parse(it)?.time } }

    // Public methods
    fun fromSimpleFormatToModel(value: String): DateModel =
        value.toDateMillis().letOr(DateModel.empty) { millis ->
            format(millis).letOr(DateModel.empty) { formatted ->
                DateModel(
                    millis = millis,
                    formatted = formatted
                )
            }
        }

    fun fromISO8610ToModel(value: String): DateModel =
        ISO8601Utils.parse(value, ParsePosition(0))?.time.letOr(DateModel.empty) { millis ->
            format(millis).letOr(DateModel.empty) { formatted ->
                DateModel(
                    millis = millis,
                    formatted = formatted
                )
            }
        }
}
