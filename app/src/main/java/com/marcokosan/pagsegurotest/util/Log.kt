package com.marcokosan.pagsegurotest.util

import com.marcokosan.pagsegurotest.BuildConfig
import com.marcokosan.pagsegurotest.archframework.ApiException
import com.marcokosan.pagsegurotest.archframework.Result
import timber.log.Timber

@Suppress("unused", "MemberVisibilityCanBePrivate")
object Log {

    private const val LOG_PREFIX = "pagseguro."

    private val MAX_FULL_TAG_LENGTH = if (BuildConfig.DEBUG) 50 else 23
    private val MAX_TAG_LENGTH = MAX_FULL_TAG_LENGTH - LOG_PREFIX.length

    fun makeTag(cls: Class<*>): String {
        return if (BuildConfig.DEBUG) {
            makeTag(cls.simpleName)
        } else {
            var str = cls.name
            if (str.length > MAX_TAG_LENGTH) {
                str = str.substring(str.length - MAX_TAG_LENGTH)
            }
            str
        }
    }

    private fun makeTag(str: String): String {
        return if (str.length > MAX_TAG_LENGTH) {
            LOG_PREFIX + str.substring(0, MAX_TAG_LENGTH - 1)
        } else LOG_PREFIX + str
    }

    /*
     * Debug log
     */
    fun d(msg: String) {
        d(LOG_PREFIX, msg)
    }

    fun d(tag: String, msg: String, t: Throwable? = null) {
        Timber.tag(tag).d(t, msg)
    }

    /*
     * Error log
     */
    fun e(tag: String, message: String, t: Throwable? = null, firebaseLog: Boolean = true) {
        Timber.tag(tag).e(t, message)

        @Suppress("ControlFlowWithEmptyBody")
        if (!BuildConfig.DEBUG && firebaseLog) {
            // Notificar erro no firabase.
        }
    }

    fun e(tag: String, failure: Result.Failure, message: String? = null) {
        if (failure.isConnectionError) {
            return
        }
        if (failure.throwable is ApiException) {
            val msg = "api error: ${failure.throwable.httpStatus}" +
                    (message?.let { ", $it" } ?: "")
            Timber.tag(tag).e(msg)
        } else {
            e(tag, message ?: "", failure.throwable)
        }
    }
}