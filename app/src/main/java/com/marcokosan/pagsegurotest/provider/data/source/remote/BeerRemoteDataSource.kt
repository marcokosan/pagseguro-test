package com.marcokosan.pagsegurotest.provider.data.source.remote

import com.marcokosan.pagsegurotest.archframework.Result
import com.marcokosan.pagsegurotest.provider.api.PagSeguroApi
import com.marcokosan.pagsegurotest.provider.data.source.BeerDataSource
import com.marcokosan.pagsegurotest.util.safeIOCall

class BeerRemoteDataSource(private val api: PagSeguroApi) : BeerDataSource {

    override suspend fun getBeers(page: Int, perPage: Int) = safeIOCall {
        val response = api.getBeers(page, perPage)
        val beers = response.body()!!.map { it.toBeer() }
        Result.Success(beers)
    }

    override suspend fun getBeerDetails(id: Long) = safeIOCall {
        val response = api.getBeerDetail(id)
        Result.Success(response.body()!!.first().toBeer())
    }
}