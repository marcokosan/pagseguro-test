package com.marcokosan.pagsegurotest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.provider.data.source.BeerRepository
import com.marcokosan.pagsegurotest.ui.BaseViewModel

private const val PER_PAGE = 25

class HomeViewModel(
    private val beerRepository: BeerRepository
) : BaseViewModel() {

    private val _beers = MutableLiveData<List<Beer>>()
    val beers: LiveData<List<Beer>> = _beers

    private val beersData: ArrayList<Beer> = ArrayList(0)

    private var currentPage = 0

    init {
        fetchBeers()
    }

    fun fetchBeers() {
        launch {
            val pageToLoad = currentPage + 1

            when (val result = beerRepository.getBeers(pageToLoad, PER_PAGE)) {
                is Result.Success -> {
                    currentPage = pageToLoad
                    beersData.addAll(result.data)
                    _beers.value = beersData
                }
                is Result.Failure -> notifyFailure(result)
            }
        }
    }
}