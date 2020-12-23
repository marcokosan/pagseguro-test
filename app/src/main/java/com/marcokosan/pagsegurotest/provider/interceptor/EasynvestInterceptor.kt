package com.marcokosan.pagsegurotest.provider.interceptor

import com.marcokosan.pagsegurotest.archframework.ApiException
import okhttp3.Interceptor
import okhttp3.Response

class EasyResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            Result
            throw ApiException(response.code())
        }
        return response
    }
}