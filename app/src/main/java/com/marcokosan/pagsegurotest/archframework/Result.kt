package com.marcokosan.pagsegurotest.archframework

@Suppress("unused")
interface Result<out T> {

    class Success<out T>(val data: T) : Result<T>

    class Failure(val throwable: Throwable) : Result<Nothing> {

        constructor(message: String) : this(Exception(message))

        val isConnectionError: Boolean get() = throwable is NoConnectionException
    }

    companion object {
        val SUCCESS = Success(Unit)
        val FAILURE = Failure(Error())
    }
}