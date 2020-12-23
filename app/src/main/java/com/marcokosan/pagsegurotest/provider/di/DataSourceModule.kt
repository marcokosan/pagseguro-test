package com.marcokosan.pagsegurotest.provider.di

import com.marcokosan.pagsegurotest.provider.data.source.remote.BeerRemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { BeerRemoteDataSource(get()) }
}