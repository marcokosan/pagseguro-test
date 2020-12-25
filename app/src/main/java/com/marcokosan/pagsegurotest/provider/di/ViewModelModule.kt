package com.marcokosan.pagsegurotest.provider.di

import com.marcokosan.pagsegurotest.ui.beerdetails.BeerDetailsViewModel
import com.marcokosan.pagsegurotest.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { BeerDetailsViewModel() }
}