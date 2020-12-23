package com.marcokosan.pagsegurotest.provider.di

import com.marcokosan.pagsegurotest.BuildConfig
import com.marcokosan.pagsegurotest.provider.adapter.PagSeguroAdapter
import com.marcokosan.pagsegurotest.provider.api.PagSeguroApi
import com.marcokosan.pagsegurotest.provider.interceptor.ConnectionInterceptor
import com.marcokosan.pagsegurotest.provider.interceptor.EasyResponseInterceptor
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Property {
    const val API_BASE_URL = "baseUrl"
}

val logInterceptor = StringQualifier("log")
val connectionInterceptor = StringQualifier("connectionInterceptor")

val pagResponseInterceptor = StringQualifier("pagInterceptor")
val pagHttpClient = StringQualifier("pagHttpClient")
val pagRetrofit = StringQualifier("pagRetrofit")
val pagMoshi = StringQualifier("pagMoshi")

private const val TIMEOUT = 10L // Seconds

val networkModule = module {

    single<Interceptor>(logInterceptor) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    single<Interceptor>(connectionInterceptor) { ConnectionInterceptor() }

    // PagSeguro api
    single<Interceptor>(pagResponseInterceptor) { EasyResponseInterceptor() }

    single(pagHttpClient) {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(get<Interceptor>(logInterceptor))
            }
            addInterceptor(get<Interceptor>(connectionInterceptor))
            addInterceptor(get<Interceptor>(pagResponseInterceptor))

            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    single(pagRetrofit) {
        val baseUrl = getProperty(Property.API_BASE_URL)

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get(pagHttpClient))
            .addConverterFactory(MoshiConverterFactory.create(get(pagMoshi)))
            .build()
    }

    single(pagMoshi) {
        Moshi.Builder()
            .add(PagSeguroAdapter)
            .build()
    }

    single {
        val retrofit: Retrofit = get(pagRetrofit)
        retrofit.create(PagSeguroApi::class.java)
    }
}
