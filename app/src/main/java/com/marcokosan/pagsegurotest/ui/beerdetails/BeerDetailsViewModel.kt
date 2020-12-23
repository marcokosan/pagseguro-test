package com.marcokosan.pagsegurotest.ui.beerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.provider.data.source.BeerRepository
import com.marcokosan.pagsegurotest.ui.BaseViewModel

class BeerDetailsViewModel(
    private val beerRepository: BeerRepository
) : BaseViewModel() {

    private val _beerDetail = MutableLiveData<Beer>()
    val beerDetail: LiveData<Beer> = _beerDetail

    var beerId = 0L
        set(value) {
            field = value
            loadBeer()
        }

    fun loadBeer() {
        launch {
            when (val result = beerRepository.getBeerDetail(beerId)) {
                is Result.Success -> {
                    _beerDetail.value = result.data
                }
                is Result.Failure -> notifyFailure(result)
            }
        }
    }
}