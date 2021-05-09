package com.revolhope.data.common.base

import com.revolhope.domain.common.model.net.State
import java.lang.Exception

/**
 * Base class for repositoryImpl. In it error management can be implemented, in this case just exception
 * control has been included.
 */
abstract class BaseRepositoryImpl {

    protected suspend inline fun <T> statefulBlock(crossinline block: suspend () -> T): State<T> =
        try {
            State.Success(data = block.invoke())
        } catch (e: Exception) {
            State.Error(e.message, throwable = e)
        }
}
