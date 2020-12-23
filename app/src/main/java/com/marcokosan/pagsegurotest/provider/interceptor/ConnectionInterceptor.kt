package com.marcokosan.pagsegurotest.provider.interceptor

import com.marcokosan.pagsegurotest.archframework.NoConnectionException
import com.marcokosan.pagsegurotest.util.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response

class ConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isConnected()) {
            Result
            throw NoConnectionException()
        }
        return chain.proceed(chain.request())
    }
}