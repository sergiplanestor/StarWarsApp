package com.revolhope.presentation.library.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revolhope.domain.common.model.net.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = Job()

    protected val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    protected val _errorResLiveData = MutableLiveData<Int>()
    val errorResLiveData: LiveData<Int> get() = _errorResLiveData

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    protected fun <T> launchAsync(
        dispatcher: CoroutineContext = Dispatchers.IO,
        asyncTask: suspend () -> State<T>,
        onSuccess: (T) -> Unit = {},
        onError: ((message: String?) -> Unit)? = null
    ) = launch {
        withContext(dispatcher) {
            asyncTask.invoke()
        }.also { state ->
            when (state) {
                is State.Success -> onSuccess.invoke(state.data)
                is State.Error -> {
                    onError?.invoke(state.message) ?: state.message?.let(_errorLiveData::setValue)
                }
            }
        }
    }
}
