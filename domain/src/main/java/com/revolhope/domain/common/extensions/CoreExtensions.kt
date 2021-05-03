package com.revolhope.domain.common.extensions

const val EMPTY_STRING = ""
const val SLASH = "/"

inline fun <T, R> T?.letOr(default: R, crossinline block: (T) -> R): R =
    this?.let { block.invoke(this) } ?: default

fun <T> blockOrNull(
    error: ((Throwable) -> Unit)? = null,
    closure: (() -> Unit)? = null,
    block: () -> T
): T? = try {
    block.invoke()
} catch (throwable: Throwable) {
    error?.invoke(throwable)
    null
} finally {
    closure?.invoke()
}

fun String.remove(toRemove: String): String = this.replace(toRemove, EMPTY_STRING)

