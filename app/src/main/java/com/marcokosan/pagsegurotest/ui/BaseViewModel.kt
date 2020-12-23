package com.marcokosan.pagsegurotest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcokosan.pagsegurotest.archframework.EventLiveData
import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.util.Log
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val tag = Log.makeTag(this.javaClass)

    val loading = MutableLiveData(false)

    val failure = EventLiveData<Result.Failure>()

    protected fun launch(call: suspend () -> Unit) {
        viewModelScope.launch {
            loading.value = true
            call()
            loading.value = false
        }
    }

    fun notifyFailure(failure: Result.Failure, tag: String? = null) {
        Log.e(tag ?: this.tag, failure)
        this.failure.value = failure
    }
}