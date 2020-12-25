package com.marcokosan.pagsegurotest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.provider.data.source.BeerRepository
import com.marcokosan.pagsegurotest.ui.BaseViewModel

private const val PER_PAGE = 25

class HomeViewModel(
    private val beerRepository: BeerRepository
) : BaseViewModel() {

    private val _beers = MutableLiveData<BeerPage>()
    val beers: LiveData<BeerPage> = _beers

    private val beerPage = BeerPage()

    init {
        fetchBeers()
    }

    fun fetchBeers() {
        launch {
            when (val result = beerRepository.getBeers(beerPage.currentPage + 1, PER_PAGE)) {
                is Result.Success -> {
                    beerPage.addPage(result.data)
                    _beers.value = beerPage
                }
                is Result.Failure -> notifyFailure(result)
            }
        }
    }
}