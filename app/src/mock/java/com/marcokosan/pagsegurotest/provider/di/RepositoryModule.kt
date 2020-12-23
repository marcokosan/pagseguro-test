package com.marcokosan.pagsegurotest.provider.di

import com.marcokosan.pagsegurotest.provider.data.source.BeerRepository
import com.marcokosan.pagsegurotest.provider.source.BeerRepositoryMock
import org.koin.dsl.module

val repositoryModule = module {
    single<BeerRepository> { BeerRepositoryMock() }
}