@file:Suppress("unused")

package com.marcokosan.pagsegurotest.util

import com.marcokosan.pagsegurotest.archframework.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any> safeIOCall(call: suspend () -> Result<T>): Result<T> =
        safeCall(Dispatchers.IO, call)

suspend fun <T : Any> safeDefaultCall(call: suspend () -> Result<T>): Result<T> =
        safeCall(Dispatchers.Default, call)

suspend fun <T : Any> safeCall(
    coroutineContext: CoroutineContext,
    call: suspend () -> Result<T>
): Result<T> = withContext(coroutineContext) {
    try {
        call.invoke()
    } catch (throwable: Throwable) {
        Result.Failure(throwable)
    }
}