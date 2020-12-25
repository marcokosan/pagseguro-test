package com.marcokosan.pagsegurotest.ui.beerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marcokosan.pagsegurotest.model.Beer
import com.marcokosan.pagsegurotest.ui.BaseViewModel

class BeerDetailsViewModel : BaseViewModel() {

    private val _beerDetail = MutableLiveData<Beer>()
    val beerDetail: LiveData<Beer> = _beerDetail

    fun setBeer(beer: Beer) {
        _beerDetail.value = beer
    }
}